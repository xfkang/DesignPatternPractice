# DesignPatternPractice
IT行业，一直讲一句话，拼到最后都拼的是“内功”，而内功往往就是指我们处理问题的思路、经验、想法，而对于开发者来说，甚至对于产品也一样，都离不开一个“宝典”，就是设计模式。接下来一段时间，我们一起借助Android源码去探索一下各种模式的原理、设计意图、优缺点，以及它所想要去解决的问题。同时结合我工作经验中的一些例子，来总结实践一下。

# 2022/02/27
设计模式是一套被反复使用的、多数人知晓的、经过分类编目的、代码设计经验的总结。使用设计模式是为了重用代码、让代码更容易被他人理解、保证代码可靠性。

1.Android源码设计模式探索与实战【面向对象六大基本原则】

2.实践经验总结--图片加载框架 & MVP框架

3.详细见博客地址：https://blog.csdn.net/baobei0921/article/details/123795484

# 2022/03/02
责任链模式是行为型设计模式的一种。程序设计中，我们把责任链模式这样定义：用于进行请求者和处理者之间的解耦的一种设计模式，把同一个请求，链式按顺序传递给链上的每个处理者，如果当前处理者处理，则完成，如果当前处理者不处理，则继续向下传递。

1.Android源码设计模式探索【责任链模式】

2.实践经验总结--多系统升级设计

3.详细见博客地址：https://blog.csdn.net/baobei0921/article/details/123525653

# 2022/03/03
观察者模式是行为型设计模式的一种。程序设计中，我们把观察者模式这样定义：用于进行观察者和被观察者之间的解耦的一种设计模式，定义对象间的一种一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于它的对象都可以得到通知并被自动更新。

1.Android源码设计模式探索【观察者模式】

2.实践经验总结--多系统升级设计

3.详细见博客地址：https://blog.csdn.net/baobei0921/article/details/123525874

# 2022/05/25
单例模式是创建性设计模式的一种。是我们最常见、也可能是开发者肯定会使用的一种设计模式。先从单例这个词来理解，在应用这个设计模式的时候，单例对象必须保证全局只有一个实例存在。

1.Android源码设计模式探索【单例模式】

2.实践经验总结--图片加载框架设计 & 登录注册框架设计

3.详细见博客地址：https://blog.csdn.net/baobei0921/article/details/124947364

# 2022/06/01
建造者模式是创建性设计模式的一种。该模式是为了将复杂对象的构建过程和组装过程相分离，对外不可见。

1.Android源码设计模式探索【建造者模式】

2.实践经验总结--自定义NavigationBar & 通用Dialog框架设计

3.详细见博客地址：https://blog.csdn.net/baobei0921/article/details/124966396

# 2022/06/02
工厂模式是创建性设计模式的一种。从工厂这个词来理解，工厂一般是用来生成产品的统一接口，例如：我们可以从4S点预定某台车，而不需要关心车是怎么制造出来的。

1.Android源码设计模式探索【工厂模式】

2.实践经验总结--自定义存储框架实现 & 自定义图片加载框架实现

3.详细见博客地址：https://blog.csdn.net/baobei0921/article/details/125082653

# 2022/06/15
装饰者是结构性设计模式的一种。装饰器模式（Decorator Pattern）允许向一个现有的对象添加新的功能，同时又不改变其结构。它是作为现有的类的一个包装。

1.Android源码设计模式探索与实战【装饰者模式】

2.实践经验总结--自定义RecycleView的实现

3.详细见博客地址：https://blog.csdn.net/baobei0921/article/details/125173034

# 2022/06/16
模板模式是行为型设计模式的一种。模板方法模式一般用作，定义一个操作中的算法的骨架，而将一些步骤延迟到子类中。模板方法使得子类可以不改变一个算法的结构即可重定义该算法的某些特定步骤。

1.Android源码设计模式探索与实战【模板模式】

2.实践经验总结--BaseActivity实现

3.详细见博客地址：https://blog.csdn.net/baobei0921/article/details/124667658