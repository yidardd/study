package consistenthash;

/**
 * hash 函数接口 
 *  
 * @author sundoctor 
 *  
 */  
public interface HashFunction {  
  
    /** 
     * hash函数 
     *  
     * @param key 
     * @return 
     */  
    Long hash(String key);  
}  