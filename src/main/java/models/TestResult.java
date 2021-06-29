package models;

public class TestResult<T1,T2> {
    public T1 httpResponse;
    public T2 objectResponse;

    public TestResult(T1 resp, T2 obj) {
        this.httpResponse=resp;
        this.objectResponse =obj;
    }
}
