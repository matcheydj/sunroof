package com.infoservice.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 
 * @author sunzj
 *
 */
public abstract class ObjectUtil
{
    public static Object deepCopy(Serializable obj)
    {
        try
        {
            return deserialize(serialize(obj));
        }
        catch (Exception e)
        {

            e.printStackTrace();
            throw new RuntimeException("对象复制失败");
        }
    }

    public static byte[] serialize(Serializable obj)
    {

        try
        {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.close();
            return bos.toByteArray();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }

    public static Object deserialize(byte[] bytes)
    {

        try
        {
            ObjectInputStream ois = new ObjectInputStream(
                    new ByteArrayInputStream(bytes));
            Object object = ois.readObject();
            ois.close();
            return object;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }

    public static boolean isLetter(String str)
    {
        Pattern pa = Pattern.compile("^[A-Za-z]+$");// 字母
        Matcher m = pa.matcher(str);
        return m.find();
    }

}
