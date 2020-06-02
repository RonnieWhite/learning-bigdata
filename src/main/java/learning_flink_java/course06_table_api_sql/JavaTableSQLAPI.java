package learning_flink_java.course06_table_api_sql;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.io.CsvReader;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.table.api.java.BatchTableEnvironment;
import org.apache.flink.types.Row;

public class JavaTableSQLAPI {
    public static void main(String[] args) throws Exception {
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        DataSet<person> csv = env.readCsvFile("E:/data/flink/input/person.csv")
                .ignoreFirstLine().pojoType(person.class, "name", "age", "job");
        BatchTableEnvironment TableEnv = BatchTableEnvironment.create(env);
        Table table = TableEnv.fromDataSet(csv);
        TableEnv.registerTable("person", table);
        Table resultTable = TableEnv.sqlQuery("SELECT avg(age) AS age FROM person");
        DataSet<Row> result = TableEnv.toDataSet(resultTable, Row.class);
        result.print();
    }
}
