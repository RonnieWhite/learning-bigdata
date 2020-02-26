package learning_flink.course03_code_module;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;


public class StreamingKeySelector {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> text = env.socketTextStream("localhost", 9999);
        text.flatMap(new FlatMapFunction<String, WC>() {
            @Override
            public void flatMap(String s, Collector<WC> collector) {
                String[] tokens = s.toLowerCase().split(" ");
                for (String token : tokens) {
                    if (token.length() > 0) {
                        collector.collect(new WC(token, 1));
                    }
                }
            }
        }).keyBy(new KeySelector<WC, String>() {
            @Override
            public String getKey(WC wc) {
                return wc.word;
            }
        }).timeWindow(Time.seconds(5))
                .sum("count").print().setParallelism(1);
        env.execute();
    }

    public static class WC {
        private String word;
        private int count;

        public WC() {
        }

        public WC(String word, int count) {
            this.word = word;
            this.count = count;
        }

        @Override
        public String toString() {
            return "WC(" + "word='" + word + '\'' + ",count=" + count + ')';
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
