package com.example.demo.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Set;

import com.example.demo.entities.FresherInfo;
import com.example.demo.entities.Hobbies;
import com.example.demo.entities.Projects;
import com.example.demo.entities.SkillInfo;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class GenerateFresherResumePdf {

	public static ByteArrayInputStream letfAlignment(FresherInfo fresher) {
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {
			Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			fontTitle.setSize(20);

			Paragraph pTitle = new Paragraph("Resume", fontTitle);
			pTitle.setAlignment(Paragraph.ALIGN_TOP);
			pTitle.setAlignment(Paragraph.ALIGN_CENTER);
			pTitle.setSpacingAfter(14);

			Font fontPersonalDetails = FontFactory.getFont(FontFactory.TIMES_ROMAN);
			fontPersonalDetails.setSize(14);

			Paragraph pFirstName = new Paragraph(fresher.getFresherName(), fontPersonalDetails);
			pFirstName.setAlignment(Paragraph.ALIGN_LEFT);

			Paragraph pEmail = new Paragraph(fresher.getEmail(), fontPersonalDetails);
			pEmail.setAlignment(Paragraph.ALIGN_LEFT);

			Paragraph pContactno = new Paragraph(String.valueOf(fresher.getContactNo()), fontPersonalDetails);
			pContactno.setAlignment(Paragraph.ALIGN_LEFT);

			Paragraph pAddress = new Paragraph(fresher.getAddress(), fontPersonalDetails);
			pAddress.setAlignment(Paragraph.ALIGN_LEFT);
			
			
			Paragraph pAge = new Paragraph(String.valueOf(fresher.getAge()), fontPersonalDetails);
			pAge.setAlignment(Paragraph.ALIGN_LEFT);
			pAge.setSpacingAfter(8);
			Paragraph pSpace = new Paragraph(
					"----------------------------------------------------------------------------------------------------------------------------------");
			pSpace.setSpacingAfter(8);

			Font fontSideHeadings = FontFactory.getFont(FontFactory.COURIER_BOLD,14 );
			fontSideHeadings.setSize(15);
			Chunk academic = new Chunk("ACADEMIC", fontSideHeadings);
			academic.setUnderline(1f, -2f);
			
			
			Paragraph pSSc = new Paragraph("* SSC : "+String.valueOf(fresher.getAcademic().getSscPercent())+"%",fontPersonalDetails);
			pSSc.setSpacingBefore(5);
			Paragraph pHsc = new Paragraph("* HSC : "+String.valueOf(fresher.getAcademic().getHscPercent())+"%",fontPersonalDetails);
			pHsc.setSpacingBefore(5);
			Paragraph pDegree = new Paragraph("* Degree : "+String.valueOf(fresher.getAcademic().getDegree()),fontPersonalDetails);
			pDegree.setSpacingBefore(8);
			Paragraph pBranch = new Paragraph("* Branch : "+String.valueOf(fresher.getAcademic().getBranch()),fontPersonalDetails);
			pBranch.setSpacingBefore(5);
			Paragraph pDegreePercent = new Paragraph("* Degree% : "+String.valueOf(fresher.getAcademic().getDegreePercent())+"%",fontPersonalDetails);
			pDegreePercent.setSpacingBefore(5);
			Paragraph pNoOfBacklogs = new Paragraph("* No_Of_Backlogs : "+String.valueOf(fresher.getAcademic().getNoOfBacklogs()),fontPersonalDetails);
			pNoOfBacklogs.setSpacingBefore(8);
			Paragraph pYeargap = new Paragraph("* Year_gap : "+String.valueOf(fresher.getAcademic().getYeargap()),fontPersonalDetails);
			pYeargap.setSpacingBefore(8);
			
		
			
			Chunk Hobbies= new Chunk("Hobbies", fontSideHeadings);
			Hobbies.setUnderline(1f, -2f);
			
			Chunk Project = new Chunk("PROJECT", fontSideHeadings);
			Project.setUnderline(1f, -2f);
			
			Chunk Skills = new Chunk("SKILLS", fontSideHeadings);
			Skills.setUnderline(1f, -2f);
			
			
			
			List<Hobbies> HobbiesDetails = fresher.getHobbies();
			List<Projects> ProjectDetails = fresher.getProjects();
			List<SkillInfo> SkillsDetails = fresher.getSkills();
			
			PdfWriter.getInstance(document, out);
			document.open();
			document.add(pTitle);
			document.add(pFirstName);
			document.add(pEmail);
			document.add(pContactno);
			document.add(pAddress);
			document.add(pAge);
			
			document.add(pSpace);
			
			document.add(academic);
			document.add(pSSc);
			document.add(pHsc);
			document.add(pDegree);
			document.add(pBranch);
			document.add(pDegreePercent);
			document.add(pNoOfBacklogs);
			document.add(pYeargap);
			
			document.add(pSpace);
			document.add(Project);
			
			for(Projects pp : ProjectDetails)
			{
				Paragraph pProjects = new Paragraph(" " + pp.getProjectName()  ,fontPersonalDetails);
				pProjects.setSpacingBefore(5);
				document.add(pProjects);
			}
			
			document.add(pSpace);
			document.add(Skills);
			for(SkillInfo ss : SkillsDetails)
			{
				Paragraph pSkillInfo = new Paragraph(" " + ss.getSkillName() ,fontPersonalDetails);
				pSkillInfo.setSpacingBefore(5);
				document.add(pSkillInfo);
			}
			
			document.add(pSpace);
			document.add(Hobbies);
			
			for(Hobbies hh : HobbiesDetails)
			{
				Paragraph pHobbies = new Paragraph(" " + hh.getHobbyName() ,fontPersonalDetails);
				pHobbies.setSpacingBefore(5);
				document.add(pHobbies);
			}
			
			
			document.close();

		} catch (DocumentException ex) {

			System.out.println(ex);
		}

		return new ByteArrayInputStream(out.toByteArray());
	}
	
	
	public static ByteArrayInputStream rightAlignment(FresherInfo fresher) {
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {
			Font fontTitle = FontFactory.getFont(FontFactory.COURIER_BOLDOBLIQUE);
			fontTitle.setSize(20);

			Paragraph pTitle = new Paragraph("Resume", fontTitle);
			pTitle.setAlignment(Paragraph.ALIGN_TOP);
			pTitle.setAlignment(Paragraph.ALIGN_LEFT);
			pTitle.setSpacingAfter(8);

			Font fontPersonalDetails = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			fontPersonalDetails.setSize(12);

			Paragraph pFirstName = new Paragraph(fresher.getFresherName(), fontPersonalDetails);
			pFirstName.setAlignment(Paragraph.ALIGN_RIGHT);

			Paragraph pEmail = new Paragraph(fresher.getEmail(), fontPersonalDetails);
			pEmail.setAlignment(Paragraph.ALIGN_RIGHT);

			Paragraph pContactno = new Paragraph(String.valueOf(fresher.getContactNo()), fontPersonalDetails);
			pContactno.setAlignment(Paragraph.ALIGN_RIGHT);

			Paragraph pAddress = new Paragraph(fresher.getAddress(), fontPersonalDetails);
			pAddress.setAlignment(Paragraph.ALIGN_RIGHT);
			
			Paragraph pAge = new Paragraph(String.valueOf(fresher.getAge()), fontPersonalDetails);
			pAge.setAlignment(Paragraph.ALIGN_RIGHT);
			Paragraph pSpace = new Paragraph(
					"----------------------------------------------------------------------------------------------------------------------------------");
			pSpace.setSpacingAfter(10);

			Font fontSideHeadings = FontFactory.getFont(FontFactory.COURIER_BOLD,  12, Font.NORMAL, BaseColor.BLUE);
			fontSideHeadings.setSize(15);
			Chunk academic = new Chunk("ACADEMIC", fontSideHeadings);
			academic.setUnderline(1f, -2f);
			
			Paragraph pDegree = new Paragraph("* Degree : "+String.valueOf(fresher.getAcademic().getDegree()),fontPersonalDetails);
			pDegree.setSpacingBefore(8);
			Paragraph pBranch = new Paragraph("* Branch : "+String.valueOf(fresher.getAcademic().getBranch()),fontPersonalDetails);
			pBranch.setSpacingBefore(5);
			Paragraph pDegreePercent = new Paragraph("* Degree% : "+String.valueOf(fresher.getAcademic().getDegreePercent())+"%",fontPersonalDetails);
			pDegreePercent.setSpacingBefore(5);
			Paragraph pNoOfBacklogs = new Paragraph("* No_Of_Backlogs : "+String.valueOf(fresher.getAcademic().getNoOfBacklogs()),fontPersonalDetails);
			pNoOfBacklogs.setSpacingBefore(8);
			Paragraph pYeargap = new Paragraph("* Year_gap : "+String.valueOf(fresher.getAcademic().getYeargap()),fontPersonalDetails);
			pYeargap.setSpacingBefore(8);
			
		
			
			Paragraph pHsc = new Paragraph("* HSC : "+String.valueOf(fresher.getAcademic().getHscPercent())+"%",fontPersonalDetails);
			pHsc.setSpacingBefore(5);
			
			Paragraph pSSc = new Paragraph("* SSC : "+String.valueOf(fresher.getAcademic().getSscPercent())+"%",fontPersonalDetails);
			pSSc.setSpacingBefore(5);
			
			Chunk Hobbies= new Chunk("Hobbies", fontSideHeadings);
			Hobbies.setUnderline(1f, -2f);
			
			Chunk Project = new Chunk("PROJECT", fontSideHeadings);
			Project.setUnderline(1f, -2f);
			
			Chunk Skills = new Chunk("SKILLS", fontSideHeadings);
			Skills.setUnderline(1f, -2f);
			
			
			
			List<Hobbies> HobbiesDetails = fresher.getHobbies();
			List<Projects> ProjectDetails = fresher.getProjects();
			List<SkillInfo> SkillsDetails = fresher.getSkills();
			
			PdfWriter.getInstance(document, out);
			document.open();
			document.add(pTitle);
			document.add(pFirstName);
			document.add(pEmail);
			document.add(pContactno);
			document.add(pAddress);
			document.add(pAge);
			
			document.add(pSpace);
			document.add(academic);
			document.add(pHsc);
			document.add(pSSc);
			document.add(pDegree);
			document.add(pBranch);
			document.add(pDegreePercent);
			document.add(pNoOfBacklogs);
			document.add(pYeargap);
			
			
			document.add(pSpace);
			document.add(Project);
			
			for(Projects pp : ProjectDetails)
			{
				Paragraph pProjects = new Paragraph(" " + pp.getProjectName()   ,fontPersonalDetails);
				pProjects.setSpacingBefore(5);
				document.add(pProjects);
			}
			
			document.add(pSpace);
			document.add(Skills);
			for(SkillInfo ss : SkillsDetails)
			{
				Paragraph pSkillInfo = new Paragraph(" " + ss.getSkillName() ,fontPersonalDetails);
				pSkillInfo.setSpacingBefore(5);
				document.add(pSkillInfo);
			}
			
			document.add(pSpace);
			document.add(Hobbies);
			
			for(Hobbies hh : HobbiesDetails)
			{
				Paragraph pHobbies = new Paragraph(" " + hh.getHobbyName() ,fontPersonalDetails);
				pHobbies.setSpacingBefore(5);
				document.add(pHobbies);
			}
			
			
			document.close();

		} catch (DocumentException ex) {

			System.out.println(ex);
		}

		return new ByteArrayInputStream(out.toByteArray());
	}
	
	public static ByteArrayInputStream centerAlignment(FresherInfo fresher) {
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {
		//	Font fontTitle = FontFactory.getFont(FontFactory.COURIER_BOLD ,BaseColor.RED);
			Font fontTitle = FontFactory.getFont(FontFactory.COURIER_BOLD);
	
			fontTitle.setSize(28);

			Paragraph pTitle = new Paragraph("Resume", fontTitle);
			pTitle.setAlignment(Paragraph.ALIGN_TOP);
			pTitle.setAlignment(Paragraph.ALIGN_CENTER);
			pTitle.setSpacingAfter(14);

			Font fontPersonalDetails = FontFactory.getFont(FontFactory.TIMES_ROMAN);
			fontPersonalDetails.setSize(14);

			Paragraph pFirstName = new Paragraph(fresher.getFresherName(), fontPersonalDetails);
			pFirstName.setAlignment(Paragraph.ALIGN_CENTER);

			Paragraph pEmail = new Paragraph(fresher.getEmail(), fontPersonalDetails);
			pEmail.setAlignment(Paragraph.ALIGN_CENTER);

			Paragraph pContactno = new Paragraph(String.valueOf(fresher.getContactNo()), fontPersonalDetails);
			pContactno.setAlignment(Paragraph.ALIGN_CENTER);

			Paragraph pAddress = new Paragraph(fresher.getAddress(), fontPersonalDetails);
			pAddress.setAlignment(Paragraph.ALIGN_CENTER);
			
			
			Paragraph pAge = new Paragraph(String.valueOf(fresher.getAge()), fontPersonalDetails);
			pAge.setAlignment(Paragraph.ALIGN_CENTER);
			pAge.setSpacingAfter(8);
			Paragraph pSpace = new Paragraph(
					"----------------------------------------------------------------------------------------------------------------------------------");
			pSpace.setSpacingAfter(10);

			Font fontSideHeadings = FontFactory.getFont(FontFactory.COURIER_BOLD, 12, Font.NORMAL, BaseColor.RED);
			fontSideHeadings.setSize(16);
			Chunk academic = new Chunk("ACADEMICS", fontSideHeadings);
			academic.setUnderline(1f, -2f);
			
			Paragraph pDegree = new Paragraph("* Degree : "+String.valueOf(fresher.getAcademic().getDegree()),fontPersonalDetails);
			pDegree.setSpacingBefore(4);
			Paragraph pBranch = new Paragraph("* Branch : "+String.valueOf(fresher.getAcademic().getBranch()),fontPersonalDetails);
			pBranch.setSpacingBefore(4);
			Paragraph pDegreePercent = new Paragraph("* Degree% : "+String.valueOf(fresher.getAcademic().getDegreePercent())+"%",fontPersonalDetails);
			pDegreePercent.setSpacingBefore(4);
			Paragraph pNoOfBacklogs = new Paragraph("* No_Of_Backlogs : "+String.valueOf(fresher.getAcademic().getNoOfBacklogs()),fontPersonalDetails);
			pNoOfBacklogs.setSpacingBefore(4);
			Paragraph pYeargap = new Paragraph("* Year_gap : "+String.valueOf(fresher.getAcademic().getYeargap()),fontPersonalDetails);
			pYeargap.setSpacingBefore(4);
			
		
			
			Paragraph pHsc = new Paragraph("* HSC : "+String.valueOf(fresher.getAcademic().getHscPercent())+"%",fontPersonalDetails);
			pHsc.setSpacingBefore(4);
			
			Paragraph pSSc = new Paragraph("* SSC : "+String.valueOf(fresher.getAcademic().getSscPercent())+"%",fontPersonalDetails);
			pSSc.setSpacingBefore(4);
			
			Chunk Hobbies= new Chunk("HOBBIES", fontSideHeadings);
			Hobbies.setUnderline(1f, -2f);
			
			Chunk Project = new Chunk("PROJECTS", fontSideHeadings);
			Project.setUnderline(1f, -2f);
			
			Chunk Skills = new Chunk("SKILLS", fontSideHeadings);
			Skills.setUnderline(1f, -2f);
			
			
			
			List<Hobbies> HobbiesDetails = fresher.getHobbies();
			List<Projects> ProjectDetails = fresher.getProjects();
			List<SkillInfo> SkillsDetails = fresher.getSkills();
			
			PdfWriter.getInstance(document, out);
			document.open();
			document.add(pTitle);
			document.add(pFirstName);
			document.add(pEmail);
			document.add(pContactno);
			document.add(pAddress);
			document.add(pAge);
			
			document.add(pSpace);
			document.add(academic);
			document.add(pHsc);
			document.add(pSSc);
			document.add(pDegree);
			document.add(pBranch);
			document.add(pDegreePercent);
			document.add(pNoOfBacklogs);
			document.add(pYeargap);
		
			
			document.add(pSpace);
			document.add(Project);
			
			for(Projects pp : ProjectDetails)
			{
				Paragraph pProjects = new Paragraph(" " + pp.getProjectName()  ,fontPersonalDetails);
				pProjects.setSpacingBefore(5);
				document.add(pProjects);
			}
			
			document.add(pSpace);
			document.add(Skills);
			for(SkillInfo ss : SkillsDetails)
			{
				Paragraph pSkillInfo = new Paragraph(" " + ss.getSkillName() ,fontPersonalDetails);
				pSkillInfo.setSpacingBefore(5);
				document.add(pSkillInfo);
			}
			
			document.add(pSpace);
			document.add(Hobbies);
			
			for(Hobbies hh : HobbiesDetails)
			{
				Paragraph pHobbies = new Paragraph(" " + hh.getHobbyName() ,fontPersonalDetails);
				pHobbies.setSpacingBefore(5);
				document.add(pHobbies);
			}
			
			
			document.close();

		} catch (DocumentException ex) {

			System.out.println(ex);
		}

		return new ByteArrayInputStream(out.toByteArray());
	}


}