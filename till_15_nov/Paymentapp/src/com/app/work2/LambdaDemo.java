package com.app.work2;


import java.util.Optional;

@FunctionalInterface
interface Greet {
    void sayHello(String name);
 //default   void sayHello1(String name) {
    	//with some implementations
 //   }
}

public class LambdaDemo {
    public static void main(String[] args) {
        Greet greet = (name) ->
        //body
        {
        	System.out.println("Hello " + name + "!");
        	};
        greet.sayHello("Aashish");
        
        
        Calculation cobj = (i,j)->{return i+j;};
        
        Calculation cobj1 = (i,j)->{return i*j;};
        
        LambdaDemo obj = new LambdaDemo();
        obj.print(cobj1);
        obj.checkOption();
    	Optional<String> name = Optional.ofNullable(obj.getName());

        
        if(name.isPresent()) {
        	System.out.println(obj.getName());
        }
    }
    
    public void print(Calculation cobj) {
    	System.out.println("Hello " +cobj.add(3, 4));
    	
    	
    }
    
    public void checkOption() {
    	Optional<String> name = Optional.ofNullable(null);

        System.out.println("Is Present: " + name.isPresent());

        String result = name.orElse("Default Name");
        System.out.println("Result: " + result);

        name.ifPresent(n -> System.out.println("Hello " + n));

        Optional<String> city = Optional.of("Mumbai");
        System.out.println(city.orElseGet(() -> "Unknown City"));
        System.out.println(city.orElseThrow(() -> new RuntimeException("No city found")));
        
        
    }

  public String getName() {
	  return null;
  }  
    
}