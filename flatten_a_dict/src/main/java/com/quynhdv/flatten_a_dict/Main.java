package com.quynhdv.flatten_a_dict;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, Object> dict = new HashMap<>();
        dict.put("Key1", "1");
        Map<String, Object> key2 = new HashMap<>();
        key2.put("a", "2");
        key2.put("b", "3");
        Map<String, Object> c = new HashMap<>();
        c.put("d", "3");
        Map<String, Object> e = new HashMap<>();
        e.put("", "1");
        c.put("e", e);
        key2.put("c", c);
        dict.put("Key2", key2);
        System.out.println(flattenDictionary(dict));
        // {Key2.c.d=3, Key2.c.e=1, Key1=1, Key2.a=2, Key2.b=3}
    }
    @SuppressWarnings("unchecked")
    static HashMap<String, String> flattenDictionary(Map<String, Object> dict) {
        HashMap<String, String> result = new HashMap<>();
        for(var kv: dict.entrySet()){
            if(kv.getValue() instanceof String){
                result.put(kv.getKey(), (String) kv.getValue());
            } else {
                Map<String, String> subDict = flattenDictionary((Map<String, Object>) kv.getValue());
                for(var subKv: subDict.entrySet()){
                    String newKey = kv.getKey();
                    if(!subKv.getKey().isEmpty()){
                        if(!newKey.isEmpty()){
                            newKey += ".";
                        }
                        newKey += subKv.getKey();
                    }
                    result.put(newKey, subKv.getValue());
                }
            }
        }
        return result;
    }
}
/*
 * dict = {
            "Key1" : "1",
            "Key2" : {
                "a" : "2",
                "b" : "3",
                "c" : {
                    "d" : "3",
                    "e" : {
                        "" : "1"
                    }
                }
            }
        }
 */