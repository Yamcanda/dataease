package io.dataease.provider.query;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.Select;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author Junjun
 */
public class SQLUtils {
    private static final String SubstitutedParams = "DATAEASE_PATAMS_BI";
    private static final String regex = "\\$\\{(.*?)\\}";

    public static String transKeyword(String value) {
        if(value == null){
            return null;
        }else{
            return value.replaceAll("'", "\\\\'");
        }
    }

    /**
     *  判断 SQL 是否为查询语句
     */
    public static boolean isQuery(String sql){
        // 替换掉所有占位符
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sql);
        while (matcher.find()) {
            sql = sql.replace(matcher.group(), SubstitutedParams);
        }

        // 判断 SQL 类型
        try {
            Statement parse = CCJSqlParserUtil.parse(sql);
            return parse instanceof Select ;
        } catch (JSQLParserException e) {
            throw new RuntimeException(e);
        }
    }
}
