package test;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

import com.google.common.base.Optional;

public class App {

	public static void main(String[] args) throws Exception {
		System.out.println("Starting server...");
		
		MyServiceImpl implementor = new MyServiceImpl();
		JaxWsServerFactoryBean svrFactory = new JaxWsServerFactoryBean();
		svrFactory.setServiceClass(MyService.class);
		
		final String address = "http://localhost:" + Optional.fromNullable(System.getenv("PORT")).or("9000") + "/";
		System.out.println("Binding on " + address + "...");
		svrFactory.setAddress(address);
		
		svrFactory.setServiceBean(implementor);
		svrFactory.getInInterceptors().add(new LoggingInInterceptor());
		svrFactory.getOutInterceptors().add(new LoggingOutInterceptor());
		svrFactory.create();
	}

}
