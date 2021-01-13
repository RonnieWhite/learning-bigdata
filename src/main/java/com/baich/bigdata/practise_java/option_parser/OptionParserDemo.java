package com.baich.bigdata.practise_java.option_parser;


import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpecBuilder;
import org.mortbay.log.Log;

/**
 * @Author: Chenghui Bai
 * Date: 2020/11/25 15:25
 * project name: learning-bigdata
 * @PackgeName: com.baich.bigdata.practise_java.option_parser
 * @ClassName: OptionParserDemo
 * @Version:
 * @Description:
 */
public class OptionParserDemo {
    public static void main(String[] args) {
//        OptionParser parser = new OptionParser();
//        parser.accepts("name", "it is required").withRequiredArg().describedAs("data_file.txt").ofType(String.class);
//        OptionSet options = parser.parse(args);
//        if (!options.has("name")) {
//            System.out.println("error");
//            return;
//        }
//        System.out.println((String) options.valueOf("name"));

        OptionParser optionParser = new OptionParser();
        optionParser.accepts("xxx").withRequiredArg().describedAs("xxxx");
        optionParser.accepts("xyz", "xyz").withOptionalArg();
        OptionSet optionSet = optionParser.parse(args);
        if (optionSet.has("xxx")) {
            System.out.println(optionSet.valueOf("xxx"));
        }
        if (optionSet.has("xyz")) {
            System.out.println(optionSet.valueOf("xyz"));
        }
    }
}
