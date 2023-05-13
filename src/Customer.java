import java.io.*;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Customer {
    private String email;
    private String username;
    private String password;
    private String phone_num;
    private int customer_id;
    private String address;
    private Vector<Order> orders=new Vector<Order>();
    private Cart cart;
    Customer()
    {
        cart=new Cart();
    }
    private boolean isUsed(String str)
    {
        boolean b=false;
        String line =null;
        try {
            Scanner scanner=new Scanner(new FileReader("customers.txt"));
            while (scanner.hasNext())
            {
                line=scanner.next();
                if(line .equals( str))
                {
                    b = true;
                    break;
                }
            }
        }
        catch (IOException e)
        {
            System.out.println(
                    "There are some IOException");
        }
        return b;
    }
    private void log_in()
    {
        String line=null;
        boolean b=true;

            while (b)
            {
                System.out.println("enter username:");
                Scanner in=new Scanner(System.in);
                username=in.next();
                System.out.println("enter password:");
                password=in.next();
                if(isUsed(username)==false)
                {
                    System.out.println("wrong username");
                    continue;
                }
                try {
                    Scanner scanner=new Scanner(new FileReader("customers.txt"));
                    while (scanner.hasNext())
                    {
                    line = scanner.next();
                    if (line.equals(username) && scanner.next().equals(password))
                    {
                        System.out.println("successful log in");
                        b=false;
                        break;
                    }
                    else if (line.equals(username) && !scanner.next().equals(password))
                    {
                        System.out.println("wrong password\n");
                        break;
                    }

                }

            }
                catch (IOException e)
                {
                    System.out.println(
                            "There are some IOException");
                }
        }

//        while (isUsed(username)==false && isUsed(password)==false){
//            System.out.println("wrong username or password\n");
//            System.out.println("enter username:");
//            username=in.next();
//            System.out.println("enter password:");
//            password=in.next();
//        }
    }
    private void register()
    {
        System.out.println("enter username:");//
        Scanner in = new Scanner(System.in);
        username = in.next();
        while (isUsed(username)==true)
        {
            System.out.println("unavilable username please choose another");
            username=in.next();
        }
        System.out.println("enter email:");
        email = in.next();
        Pattern pattern1 = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
        Matcher matcher1 = pattern1.matcher(email);
        while (isUsed(email)==true)
        {
            System.out.println("unavilable email enter another one");
            email=in.next();
        }
        while (matcher1.matches() == false)
        {
            System.out.println("enter a valid email");
            email = in.next();
            matcher1 = pattern1.matcher(email);
        }
        System.out.println("enter a password that at lest 8 characters and contain lower and upper case letter");
        password=in.next();
        Pattern pattern2 = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%]).{8,})");
        Matcher matcher2 = pattern2.matcher(password);
        while (matcher2.matches() == false)
        {
            System.out.println("enter a valid password");
            password=in.next();
            matcher2=pattern2.matcher(password);
        }
        System.out.println("enter a valid address:");
        Scanner scanner = new Scanner(System.in);
        address=scanner.nextLine();
        System.out.println("enter your phone number:");
        phone_num= in.next();
        /*
         emailOtp otp = new emailOtp(email);

        int code = otp.sendOTP(); // send OTP code to user's email

        boolean otpVerified = false;
        while (!otpVerified) {
            otpVerified = otp.verifyOTP(code);
            if (!otpVerified) {
                System.out.println("The OTP code sent to " + email + " is not correct. We will send another one.");
                code = otp.sendOTP(); // send another OTP code if the first one was incorrect
            }
        }
        System.out.println("OTP code is correct");
        */
        try {
            FileWriter fw=new FileWriter("customers.txt",true);
            BufferedWriter bw=new BufferedWriter(fw);
            PrintWriter pw=new PrintWriter(bw);
            pw.println(username);
            pw.println(password);
            pw.println(email);
            pw.println(address );
            pw.println(phone_num );
            pw.flush();
        }
        catch (IOException e)
        {
            System.out.println("error");
        }
    }
    private void view_catalog()
    {
        Vector<Item> items=new Vector<Item>();
        String line=null;
        try {
            Scanner scanner=new Scanner(new FileReader("catalog.txt"));
            while (scanner.hasNextLine())
            {
                Item temp=new Item();
                while (true){
                    line=scanner.nextLine();temp.setName(line);
                    line=scanner.nextLine();temp.setPrice(line);
                    line=scanner.nextLine();temp.setItem_id(line);
                    line=scanner.nextLine();temp.setQuantity(line);
                    line=scanner.nextLine();temp.setBrand(line);
                    line=scanner.nextLine();temp.setCategory(line);
                    line=scanner.nextLine();temp.setDescription(line);
                    break;
                }
                items.add(temp);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (Item item:items)
        {
            System.out.println("name: "+item.getName()+", price: "+item.getPrice()+"$, brand: "+item.getBrand()+", category:"+item.getCategory()+", description: "+item.getDescription());
        }
    }
    private void add_to_cart()
    {
        String line=null,price=null;
        int quantity;
        Item item=new Item();
        System.out.println("enter the name of the product:");
        Scanner in=new Scanner(System.in);
        String str=in.nextLine();
        try {
            Scanner scanner=new Scanner(new FileReader("catalog.txt"));
            while (scanner.hasNext())
            {
                line=scanner.nextLine();
                if(line.toLowerCase().equals(str.toLowerCase()))
                {
                    item.setName(line);
                    price=scanner.nextLine();
                    item.setPrice(price);
                    item.setItem_id(scanner.nextLine());
                    item.setQuantity(scanner.nextLine());
                    item.setBrand(scanner.nextLine());
                    item.setCategory(scanner.nextLine());
                    item.setDescription(scanner.nextLine());
                    break;
                }
            }
        }
        catch (IOException e)
        {
            System.out.println(
                    "There are some IOException");
        }
        System.out.println("enter the quantity you need:");
        quantity=in.nextInt();
        if(Integer.parseInt(item.getQuantity().strip()) < quantity)
        {
            System.out.println("please enter a smaller quantity");
            quantity=in.nextInt();
        }
        cart.total += quantity*Integer.parseInt(price);
        cart.add_item(item);
    }
    private void make_order()
    {
        Scanner in=new Scanner(System.in);
        Order order=new Order();
        System.out.println("choose payment method:");
        System.out.println("1-cash\n2-credit card");
        int n=in.nextInt();
        if(n==2)
        {
            System.out.println("enter the details:");
        }
        System.out.println("choose the address:");
        System.out.println("1-registered address\n2-new address");
        n=in.nextInt();
        if (n==2)
        {
            System.out.println("enter the new address:");
            address=in.next();
        }
        System.out.println("the total is:"+this.cart.total);
        System.out.println("thank you for shopping from toffee");
    }
    public void menu()
    {
        boolean b=true;
        System.out.println("1-register\n2-log in");
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        if(n==1||n==2) {
            if(n==1)
            register();
            else if(n==2)
                log_in();
            while (b){
            System.out.println("choose an option:\n1-view catalog\n2-add to cart\n3-make order\n4-exit");
            n= in.nextInt();
            if(n==1)
                view_catalog();
            else if(n==2)
                add_to_cart();
            else if(n==3)
                make_order();
            else if (n==4){
                System.out.println("Thank you for shopping from Toffee");
                b=false;
            }
            else
                System.out.println("unavailable option");
            }
        }
        else
            System.out.println("unavailable option");
    }
}
