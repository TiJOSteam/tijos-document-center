# json - JSON

JSON(JavaScript Object Notation)是一种轻量级的数据交换格式,  是目前最见的数据交换方式， 具体请参考 <https://baike.baidu.com/item/JSON> 或 <https://en.wikipedia.org/wiki/JSON> .

TiJOS Framework中的JSON实现来自<https://github.com/stleary/JSON-java>, 支持JSONArray, JSONObject, JSONString, 具体可参考相关资料.

## Java包
tijos.framework.util

例程:

1. JSON格式编码

```java
//Generate JSON string
JSONObject studentJSONObject = new JSONObject();  
try {  
         studentJSONObject.put("name", "Jason");  
         studentJSONObject.put("id", 20130001);  
         studentJSONObject.put("phone", "13579246810");  
     } catch (JSONException e) {  
            e.printStackTrace();  
     }  
```

2. JSON格式解码

```java
final String JSON =   
			  "{" +  
				  "   \"phone\" : [\"12345678\", \"87654321\"]," +  
				  "   \"name\" : \"jack\"," +  
				  "   \"age\" : 21," +  
				  "   \"address\" : { \"country\" : \"china\", \"province\" : \"beijing\" }," +  
				  "   \"married\" : false," +  
			  "}";  
				
			  try {  
				  JSONTokener jsonTokener = new JSONTokener(JSON);  
				  JSONObject person = (JSONObject) jsonTokener.nextValue();  
				
				  JSONArray phoneArray = person.getJSONArray("phone");
				  for(int i = 0; i < phoneArray.length(); i ++)
				  {
					  System.out.println("Phone" + i);
					  System.out.println(phoneArray.getString(i));
				  }
				  
				  System.out.println(person.getString("name"));  
				  System.out.println(person.getInt("age"));  
				 
				  JSONObject addr = person.getJSONObject("address");
				  System.out.println(addr.getString("country"));
				  System.out.println(addr.getString("province"));
					  
				  System.out.println(person.getBoolean("married"));  
			  } catch (JSONException ex) {  
				   ex.printStackTrace();
			  }    
```

   ​