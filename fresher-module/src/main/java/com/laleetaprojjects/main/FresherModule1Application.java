package com.laleetaprojjects.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.laleetaprojjects.main.entity.AcademicInfo;
import com.laleetaprojjects.main.entity.FresherInfo;
import com.laleetaprojjects.main.entity.Hobbies;
import com.laleetaprojjects.main.entity.Projects;
import com.laleetaprojjects.main.entity.Registration;
import com.laleetaprojjects.main.entity.SkillInfo;

import com.laleetaprojjects.main.repo.FresherModuleRepository;
import com.laleetaprojjects.main.repo.RegistrationRepo;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class FresherModule1Application implements CommandLineRunner {
	@Autowired
	private FresherModuleRepository frepo;
	@Autowired
	private RegistrationRepo rrepo;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("hiii");
		//save details
		FresherInfo f = new FresherInfo();
		f.setFresherName("sunny");
		f.setEmail("aki@gmail");
		f.setAddress("mumbai");
		f.setContactNo("8807645398");
		f.setAge(21);
		SkillInfo s = new SkillInfo("jsp");
		SkillInfo s1 = new SkillInfo("orm");
		Projects p = new Projects("connectivity", 20016);
		Projects p1 = new Projects("orm", 20019);
		Hobbies h = new Hobbies("sketching");
		AcademicInfo a = new AcademicInfo("67.54", "91.60", "BE", "comp", "78", 0, 0);

		f.getSkills().add(s);
		f.getSkills().add(s1);
		f.getProjects().add(p);
		f.getProjects().add(p1);
		f.getHobbies().add(h);
		f.setAcademic(a);

		s.setFresherinfo(f);
		s1.setFresherinfo(f);
		p.setFresherinfo(f);
		p1.setFresherinfo(f);
		h.setFresherinfo(f);
		a.setFresherinfo(f);
		frepo.save(f);

		Registration register = new Registration();
		register.setMail("rk123@gmail.com");
		register.setPassword("Reyan276");
		register.setUsername("Reyansh");
		rrepo.save(register);
	}

	public static void main(String[] args) {
		SpringApplication.run(FresherModule1Application.class, args);
	}

}
