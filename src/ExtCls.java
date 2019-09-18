/**
 * Created by wangji on 2019/8/17.
 */
public class ExtCls {
    public static void main(String[] args) {
        B b = new B();
        System.out.println(b.getName());
    }
}


class A {
    String name = "a";


    public String getName() {
        return this.name;
    }

}

class B extends A {

    public String getName() {
        return this.name;
    }
}
