import java.util.Map;

public interface Builder {

    boolean Verify(String userName, String password);
    void Login();

    Map<String, String> getUser();
}
