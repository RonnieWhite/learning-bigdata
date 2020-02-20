package practise.day01;

public class systemTest {
    public static void main(String[] args) {
        String version;
        String vm_version;
        String class_path;
        String os_version;
        String user_name;
        String user_dir;
        String user_home;
        version = System.getProperty("java.version");
        vm_version = System.getProperty("java.vm.version");
        class_path = System.getProperty("java.class.path");
        os_version = System.getProperty("os.version");
        user_name = System.getProperty("user.name");
        user_dir = System.getProperty("user.dir");
        user_home = System.getProperty("user.home");
        System.out.println(version);
        System.out.println(vm_version);
        System.out.println(class_path);
        System.out.println(os_version);
        System.out.println(user_name);
        System.out.println(user_dir);
        System.out.println(user_home);
    }
}
