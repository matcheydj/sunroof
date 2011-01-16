package com.infoservice.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public abstract class TagUtils
{

    /**
     * 函数介绍：
     *  参数： 返回值：
     */

    public static void main(String[] args)
    {
        String sql = "select a as (select * from bb) from  (23)where ((sdfasd))((ttt))";
        System.out.println(parseHSLConditions(sql));
    }

    public static String parseHSLConditions(String sql)
    {
        List<Pair> pairs = pareToPairs('(', ')', sql);       
        char[] chs = sql.toCharArray();
        for (Pair p : pairs)
        {
            for (int i = p.left.index; i <= p.right.index; i++)
            {
                chs[i] = '#';
            }

        }
        String parseStr = new String(chs).toLowerCase();

        return sql.substring(parseStr.indexOf("from"));
    }

    public static List<Pair> pareToPairs(char left, char right, String str)
    {
        Stack<Pos> stack = new Stack<Pos>();

        Pos pos = null;
        List<Pair> pairs = new ArrayList<Pair>();

        for (int i = 0; i < str.length(); i++)
        {
            if (str.codePointAt(i) == left)
            {
                stack.push(new Pos(i, str.charAt(i)));
            }
            else if (str.codePointAt(i) == right)
            {
                pos = stack.pop();
                pairs.add(new Pair(pos, new Pos(i, str.charAt(i))));
            }

        }

        return pairs;
    }

    static class Pos
    {

        public int index;

        public char value;

        public Pos()
        {

        }

        public Pos(int index, char value)
        {
            this.index = index;
            this.value = value;
        }
    }

    static class Pair
    {
        public Pair()
        {

        }

        public Pair(Pos left, Pos right)
        {
            this.left = left;
            this.right = right;
        }

        public Pos left;

        public Pos right;

        public String toString()
        {
            return String.valueOf(left.value) + left.index
                    + String.valueOf(right.value) + right.index;
        }

    }

}
