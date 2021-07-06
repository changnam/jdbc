package jdbc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextAwareBean implements ApplicationContextAware{

		private ApplicationContext context;
		
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context = context;
		
		int cnt=1;
		String[] beanNames = context.getBeanDefinitionNames();
		System.out.println("== list of beans ("+beanNames.length+")==");
		for (String beanName : beanNames) {
			System.out.println(cnt++ + " , " + beanName+" , "+context.getBean(beanName).getClass().toString());
		}
		System.out.println("====================");
		
		cnt = 1;
		String[] allBeans = printBeans();
		System.out.println("=== all beans including beans registered by spring ("+allBeans.length+")====");
		for (String bean : allBeans) {
			System.out.println(cnt++ + " , " + bean+" , "+context.getBean(bean).getClass().toString());
		}
		System.out.println("====================");
	}

	private String[] printBeans() {
	    AutowireCapableBeanFactory autowireCapableBeanFactory = context.getAutowireCapableBeanFactory();
	    if (autowireCapableBeanFactory instanceof SingletonBeanRegistry) {
	        String[] singletonNames = ((SingletonBeanRegistry) autowireCapableBeanFactory).getSingletonNames();
	        for (String singleton : singletonNames) {
	            //System.out.println(singleton);
	        }
	        return singletonNames;
	    }
	    return null;
	}
	
}
