package com.docker.lib.testasm;


import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestMain {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("/Users/XXX/Desktop/testJVm/demo/Test.class");
        ClassReader reader = new ClassReader(fis);
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        reader.accept(new MyClassVisivator(cw), 0);
        writeToFile(cw.toByteArray(), "/Users/XXX/Desktop/testJVm/demo/TestModify.class");
    }

    static void writeToFile(byte[] bytes, String fileName) {
        try {
            (new File(fileName)).createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fileName);
            fos.write(bytes);
            fos.flush();
        } catch (Exception e) {
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class MyClassVisivator extends ClassVisitor {
        ClassVisitor mCv;

        public MyClassVisivator(ClassVisitor cv) {
            super(Opcodes.ASM6, cv);
            mCv = cv;
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
            if ("main".equals(name)) {
                MyMethodVisitor mmv = new MyMethodVisitor(mv);
                return mmv;
            }
            if ("testReturn".equals(name)) {
                MyReturnMethodVisitor mmv = new MyReturnMethodVisitor(mv);
                return mmv;
                // 工具解析出来是不一样的
                /**
                 * public static int testReturn() {
                 *         byte var0 = 100;
                 *         short var1 = 200;
                 *         Test.test01();
                 *         int var10000 = var0 + var1;
                 *         Test.test01();
                 *         return var10000;
                 *     }
                 */

                /**
                 * public static int testReturn() {
                 *     byte b = 100;
                 *     char c = ';
                 *     Test.test01();
                 *     Test.test01();
                 *     return b + c;
                 *   }
                 */
            }
            return mv;
        }

        @Override
        public void visitEnd() {
            // 类文件访问结束时候
            MethodVisitor mv = mCv.visitMethod(Opcodes.ACC_STATIC, "test03", "()V", null, null);
            if (mv != null) {
                mv.visitCode();
                mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv.visitLdcInsn("Hello In Test03!");
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
                mv.visitInsn(Opcodes.RETURN);
                mv.visitMaxs(0, 0);
                mv.visitEnd();
            }
        }
    }

    static class MyMethodVisitor extends MethodVisitor {
        MethodVisitor mMv;

        public MyMethodVisitor(MethodVisitor mv) {
            super(Opcodes.ASM6, mv);
            mMv = mv;
        }

        @Override
        public void visitInsn(int opcode) {
            if (opcode == Opcodes.RETURN) {
                System.out.println("Debug");
                mv.visitMethodInsn(Opcodes.INVOKESTATIC, "Test", "test03", "()V");
            }
            super.visitInsn(opcode);
        }

        @Override
        public void visitEnd() {
            super.visitEnd();
        }
    }

    static class MyReturnMethodVisitor extends MethodVisitor {
        MethodVisitor mMv;

        public MyReturnMethodVisitor(MethodVisitor mv) {
            super(Opcodes.ASM6, mv);
            mMv = mv;
        }

        @Override
        public void visitInsn(int opcode) {
            //opcode 注意这里的含义
            System.out.println("Debug");
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "Test", "test01", "()V");

            super.visitInsn(opcode);
        }

        @Override
        public void visitEnd() {
            super.visitEnd();
        }
    }
    // 类中的一个方法 一个方法的遍历，每个方法对应一个MyMethodVisitor进行处理
}
