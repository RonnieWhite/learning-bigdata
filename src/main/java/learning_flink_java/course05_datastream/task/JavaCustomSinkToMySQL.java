package learning_flink_java.course05_datastream.task;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.util.Collector;

public class JavaCustomSinkToMySQL {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> data = env.socketTextStream("localhost", 9999);
        SingleOutputStreamOperator<Student> studentStream = data.map(new MapFunction<String, Student>() {
            @Override
            public Student map(String value) throws Exception {
                String[] info = value.split(",");
                Student student = new Student();
                student.setId(Integer.parseInt(info[0]));
                student.setName(info[1]);
                student.setAge(Integer.parseInt(info[2]));
                return student;
            }
        });
        studentStream.addSink(new SinkToMySQL());
        env.execute("JavaCustomSinkToMySQL");
    }
}
