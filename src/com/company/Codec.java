package com.company;
import java.util.*;

public class Codec {
    public String encode(List<String> strs) {
        if(strs.size()==0)
            return Character.toString((char) 258);
        String d = Character.toString((char) 257);
        StringBuilder sb = new StringBuilder();
        for(String str:strs)
        {
            sb.append(str);
            sb.append(d);
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        String d = Character.toString((char)258);
        if(s.equals(d))
            return new ArrayList<>();

        d = Character.toString((char)257);
        return Arrays.asList(s.split(d, -1));
    }

    public String serialize(TreeNode root) {

    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {

    }

}
