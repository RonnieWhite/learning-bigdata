package practise.day01;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class JoinBean implements Writable {
    private int order_id;
    private String date_string;
    private String product_id;
    private int amount;
    private String product_name;
    private int category_id;
    private float price;

    private String flag;

    public JoinBean() {
    }

    public void set(int order_id, String date_string, String product_id, int amount, String product_name, int category_id, float price, String flag) {
        this.order_id = order_id;
        this.date_string = date_string;
        this.product_id = product_id;
        this.amount = amount;
        this.product_name = product_name;
        this.category_id = category_id;
        this.price = price;
        this.flag = flag;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public void setDate_string(String date_string) {
        this.date_string = date_string;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public int getOrder_id() {
        return order_id;
    }

    public String getDate_string() {
        return date_string;
    }

    public String getProduct_id() {
        return product_id;
    }

    public int getAmount() {
        return amount;
    }

    public String getProduct_name() {
        return product_name;
    }

    public int getCategory_id() {
        return category_id;
    }

    public float getPrice() {
        return price;
    }

    public String getFlag() {
        return flag;
    }


    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(order_id);
        out.writeUTF(date_string);
        out.writeUTF(product_id);
        out.writeInt(amount);
        out.writeUTF(product_name);
        out.writeInt(category_id);
        out.writeFloat(price);
        out.writeUTF(flag);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        order_id = in.readInt();
        date_string = in.readUTF();
        product_id = in.readUTF();
        amount = in.readInt();
        product_name = in.readUTF();
        category_id = in.readInt();
        price = in.readFloat();
        flag = in.readUTF();
    }
}
