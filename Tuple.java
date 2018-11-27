public class Tuple<A, B> {
    A a;
    B b;

    Tuple(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public A getFst() {
        return a;
    }

    public B getSnd() {
        return b;
    }

    public void setFst(A a) {
        this.a = a;
    }

    public void setSnd(B b) {
        this.b = b;
    }
}
