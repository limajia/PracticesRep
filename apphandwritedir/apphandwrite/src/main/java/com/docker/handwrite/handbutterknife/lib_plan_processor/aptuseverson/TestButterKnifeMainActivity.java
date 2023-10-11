package com.docker.handwrite.handbutterknife.lib_plan_processor.aptuseverson;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.docker.handwrite.R;
import com.docker.lib_annotation.BindView;

@BindView()
public class TestButterKnifeMainActivity extends AppCompatActivity {

    @BindView(R.id.test_butter_knife_view)
    public TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_butter_knife_main);
        // 新文件为注解对象赋值
        AptBinding.bind(this);
        mTextView.setText("测试一下ButterKnife11111");
    }
}


//注意区分：AspectJ（Hogo库，基于aj的方法耗时统计），支持静态（编译）织入，动态（类加载期、运行期）织入
//https://www.jianshu.com/p/c8c06e35e4eb Android 函数耗时统计工具之Hugo
/*
* @Aspect
public class Hugo {
  //①
  @Pointcut("within(@hugo.weaving.DebugLog *)")
  public void withinAnnotatedClass() {}

  //②
  @Pointcut("execution(!synthetic * *(..)) && withinAnnotatedClass()")
  public void methodInsideAnnotatedType() {}

  //③
  @Pointcut("execution(!synthetic *.new(..)) && withinAnnotatedClass()")
  public void constructorInsideAnnotatedType() {}

  //④
  @Pointcut("execution(@hugo.weaving.DebugLog * *(..)) || methodInsideAnnotatedType()")
  public void method() {}

  //⑤
  @Pointcut("execution(@hugo.weaving.DebugLog *.new(..)) || constructorInsideAnnotatedType()")
  public void constructor() {}

  //⑥
  @Around("method() || constructor()")
  public Object logAndExecute(ProceedingJoinPoint joinPoint) throws Throwable {
    ...
  }
 }
* */
//处理符合Hugo中的插入点（1-5）和位置（6）进行代码织入。



/*
https://juejin.cn/post/6844903941054922760#heading-11  Android AOP实战：AspectJ入门
@Aspect 用它声明一个类，表示一个需要执行的切面。
@Pointcut 声明一个切点。
@Before/@After/@Around/...（统称为Advice类型） 声明在切点前、后、中执行切面代码。
*/


    //AspectJ和asm比较有什么劣势
    //AspectJ 和 ASM 都是 Java 中用于字节码操作的工具，但它们有不同的设计目标和使用场景，因此具有不同的优势和劣势。以下是 AspectJ 相对于 ASM 的一些劣势：
    //
    //    复杂性：AspectJ 提供了更高级的面向切面编程语法，这使得它更容易编写和理解与切面相关的代码。相比之下，ASM 更底层，需要编写更多的字节码操作代码，因此更复杂和容易出错。但也正因为如此，ASM 更灵活，可以实现更细粒度的操作。
    //
    //    学习曲线：AspectJ 的学习曲线相对较低，特别是对于开发人员来说，因为它提供了一种声明性的方式来定义切面和通知。相反，ASM 的学习曲线较陡峭，需要熟悉字节码的结构和操作码。
    //
    //    功能丰富性：AspectJ 提供了丰富的面向切面编程功能，包括不同类型的通知（前置、后置、环绕等）、切点表达式、切面继承等功能。ASM 则更专注于字节码级别的操作，不提供直接的面向切面编程功能。这意味着在 ASM 中实现类似的功能可能需要更多的代码和工作。
    //
    //    总的来说，AspectJ 更适合那些希望以【声明性和高级方式】实现面向切面编程的开发者，而且它在静态织入方面具有明显优势。ASM 则更适用于需要【更精细控制和灵活性】的情况，但它的使用可能需要更多的时间和努力。选择哪个工具取决于您的具体需求和对字节码操作的熟悉程度。


//Javassist & ASM 对比
//Javassist和ASM都是用于Java字节码操作的工具，它们允许您在运行时动态地创建、修改和分析Java字节码。然而，它们在设计和使用上有一些区别。下面是它们之间的对比：
//        1. 设计和使用方式：
//        - Javassist：Javassist是一个更高级的字节码操作工具，它提供了更简单的API，使开发者可以更容易地创建和修改字节码。它采用了类似于面向对象编程的方式，开发者可以直接操作Java类的属性和方法，并且通常更易于理解和使用。这使得Javassist在许多场景下都更加适合。
//        - ASM：ASM是一个更底层的字节码操作工具，它提供了更灵活的控制，但也更复杂。ASM需要开发者更深入地了解Java字节码的结构和规范，因此它对于高级用户和需要更多控制的场景可能更合适。
//
//        2. 性能：
//        - ASM：由于ASM更接近字节码的底层表示，因此通常比Javassist更快。这使得ASM在需要高性能的应用程序中更有竞争力。
//        - Javassist：虽然Javassist在某些情况下性能可能略逊于ASM，但在大多数情况下，性能差距可能并不显著，而且Javassist的使用更加方便。
//
//        3. 学习曲线：
//        - Javassist：由于其较高级别的API和更直观的设计，Javassist通常对于初学者来说更容易学习和使用。
//        - ASM：ASM的学习曲线较陡峭，因为它需要对Java字节码的结构和规范有深入的理解。初学者可能会觉得它更具挑战性。
//
//        4. 应用场景：
//        - Javassist：适用于许多常见的字节码操作任务，如动态代理、AOP（面向切面编程）、代码生成等。如果您需要执行相对简单的字节码操作，Javassist可能是一个不错的选择。
//        - ASM：适用于更复杂的字节码操作任务，需要更细粒度的控制，或者需要最大程度地优化性能的情况。ASM在字节码级别提供了更多的自由度。
//
//        综上所述，选择使用Javassist还是ASM取决于您的具体需求和经验水平。如果您是初学者或需要完成相对简单的任务，Javassist可能更合适。如果您需要更高级的控制和性能，或者对Java字节码有深入的了解，那么ASM可能更适合您的需求。
//
// Javassist & ASM 对比
//Javassist源代码级API比ASM中实际的字节码操作更容易使用
//Javassist在复杂的字节码级操作上提供了更高级别的抽象层。Javassist源代码级API只需要很少的字节码知识，甚至不需要任何实际字节码知识，因此实现起来更容易、更快。
//Javassist使用反射机制，这使得它比运行时使用Classworking技术的ASM慢。
//总的来说ASM比Javassist快得多，并且提供了更好的性能。Javassist使用Java源代码的简化版本，然后将其编译成字节码。这使得Javassist非常容易使用，但是它也将字节码的使用限制在Javassist源代码的限制之内。
//总之，如果有人需要更简单的方法来动态操作或创建Java类，那么应该使用Javassist API 。如果需要注重性能地方，应该使用ASM库。



//Javassist 和aspectj的区别【编程方式aop，声明方式aop】
//Javassist和AspectJ都是用于实现面向切面编程（AOP）的Java工具，但它们之间有一些重要的区别，包括以下方面：
//
//        1. **设计和使用方式**:
//        - Javassist：Javassist是一个字节码操作库，它允许您在编译时或运行时修改Java类的字节码。它提供了相对简单的[API]，可以用于创建、修改和分析类的字节码。Javassist更侧重于底层的字节码级别操作，适用于对类的结构进行较低级别的修改。
//        - AspectJ：AspectJ是一个更高级的AOP框架，它使用注解或XML配置来定义切面、通知和切点，并允许您以声明性的方式实现切面。它的设计目标是更易于管理和维护复杂的切面逻辑。AspectJ提供了更高级的概念，如切面、通知、切点等，以便更容易地实现AOP。
//
//        2. **功能和功能**:
//        - Javassist：Javassist主要用于字节码级别的操作，可用于创建、修改和分析类的字节码。它通常用于实现更低级别的字节码增强和动态代理。
//        - AspectJ：AspectJ专注于AOP，提供了更高级别的概念，如切面、通知、切点等。它允许您更容易地实现横切关注点，如日志记录、性能监视、事务管理等。AspectJ提供了更丰富的语法来描述切面逻辑。
//
//        3. **学习曲线**:
//        - Javassist：相对于AspectJ，Javassist的学习曲线通常较为平缓，因为它是一个较低级别的库，更接近Java字节码的操作。
//        - AspectJ：AspectJ的学习曲线可能较陡峭，因为它引入了一些复杂的概念和语法，需要学习如何定义和使用切面、通知等。
//
//        4. **应用场景**:
//        - Javassist：适用于需要在较低级别操作字节码的场景，如性能优化、动态代理、字节码生成等。
//        - AspectJ：适用于需要处理横切关注点的场景，如日志、事务、安全性、性能监控等。它更适合大型项目和复杂应用，以管理和维护切面逻辑。
//
//        综上所述，Javassist和AspectJ都是有用的工具，但它们的适用场景和复杂性不同。选择哪个工具取决于您的具体需求和项目的规模。如果您需要更高级的AOP支持和复杂的切面逻辑，AspectJ可能更适合您的需求。如果您只需要对类的字节码进行一些简单的操作，Javassist可能足够。
