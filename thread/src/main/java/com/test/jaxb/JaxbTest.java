package com.test.jaxb;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author <a href="mailto:15268179013@139.com">yida</a>
 * @Version 2020-04-23 16:11
 * @Version 1.0
 * @Description JaxbTest
 */
public class JaxbTest {
  public static void main(String[] args) {
    try {
      //创建JAXBContext对象，注意一定要传@XmlRootElement的所标记的类的类型
      JAXBContext jaxbContext = JAXBContext.newInstance(User.class,Attr.class);
      //拿到xml解析对象
      Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

      String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
          "<user xmls=\"http://s3.amazonaws.com/doc/2006-03-01/\">\n" +
          "    <password id=\"333\" secret=\"@#$\"/>\n" +
          "    <name>nicole</name>\n" +
          "    <age>18</age>\n" +
          "</user>";

      //解析为bean
      User bean  = (User) unmarshaller.unmarshal(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));
      System.out.println(bean.toString());

      //将bean解析为xml字符串
      Marshaller marshaller = jaxbContext.createMarshaller();
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      marshaller.marshal(bean,byteArrayOutputStream);
      byteArrayOutputStream.flush();
      byteArrayOutputStream.close();
      String s = new String(byteArrayOutputStream.toByteArray(), "utf-8");
      System.out.println(s);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
