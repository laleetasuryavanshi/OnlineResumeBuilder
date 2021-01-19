package com.example.demo.util;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Set;

import com.example.demo.entities.Experienced;
import com.example.demo.entities.Project;
import com.example.demo.entities.Skill;

public class GenerateExperiencedResumePdf {

	public static ByteArrayInputStream leftAlignment(Experienced experienced) {
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {
			Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
			fontTitle.setSize(20);

			Paragraph pTitle = new Paragraph("Resume", fontTitle);
			pTitle.setAlignment(Paragraph.ALIGN_TOP);
			pTitle.setAlignment(Paragraph.ALIGN_CENTER);
			pTitle.setSpacingAfter(8);

			Font fontPersonalDetails = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			fontPersonalDetails.setSize(12);

			Paragraph pName = new Paragraph(experienced.getFirstName() + " " + experienced.getLastName(),
					fontPersonalDetails);
			pName.setAlignment(Paragraph.ALIGN_LEFT);

			Paragraph pMailId = new Paragraph(experienced.getEmail(), fontPersonalDetails);
			pMailId.setAlignment(Paragraph.ALIGN_LEFT);

			Paragraph pPhone = new Paragraph(experienced.getPhone(), fontPersonalDetails);
			pPhone.setAlignment(Paragraph.ALIGN_LEFT);

			Paragraph pAdress = new Paragraph(
					experienced.getCity() + ", " + experienced.getState() + "- " + experienced.getPincode(),
					fontPersonalDetails);
			pAdress.setAlignment(Paragraph.ALIGN_LEFT);
			pAdress.setSpacingAfter(10);
			Paragraph pSpace = new Paragraph(
					"----------------------------------------------------------------------------------------------------------------------------------");
			pSpace.setSpacingAfter(10);

			Font fontSideHeadings = FontFactory.getFont(FontFactory.COURIER_BOLD, 12, Font.NORMAL, BaseColor.LIGHT_GRAY);
			fontSideHeadings.setSize(15);
			Chunk education = new Chunk("EDUCATION", fontSideHeadings);
			education.setUnderline(1f, -2f);

			Paragraph pDegree = new Paragraph(
					"* Degree : " + String.valueOf(experienced.getEducation().getDegreePercent()) + "%",
					fontPersonalDetails);
			pDegree.setSpacingBefore(8);

			Paragraph pHsc = new Paragraph("* HSC : " + String.valueOf(experienced.getEducation().getHsc()) + "%",
					fontPersonalDetails);
			pHsc.setSpacingBefore(5);

			Paragraph pSSc = new Paragraph("* SSC : " + String.valueOf(experienced.getEducation().getSsc()) + "%",
					fontPersonalDetails);
			pSSc.setSpacingBefore(5);

			Chunk workEx = new Chunk("WORK EXPERIENCE", fontSideHeadings);
			workEx.setUnderline(1f, -2f);

			SimpleDateFormat formatFromDate = new SimpleDateFormat("d MMMM yyyy");
			String fromDate = formatFromDate.format(experienced.getWorkex().getFromDate());

			SimpleDateFormat formatToDate = new SimpleDateFormat("d MMMM yyyy");
			String toDate = formatToDate.format(experienced.getWorkex().getToDate());

			Paragraph pWorkExperience = new Paragraph(
					"I was working in " + experienced.getWorkex().getEmployerName() + " as "
							+ experienced.getWorkex().getJobTitle() + " in " + experienced.getWorkex().getWcity() + ", "
							+ experienced.getWorkex().getWstate() + ", from " + fromDate + " to " + toDate,
					fontPersonalDetails);
			pWorkExperience.setSpacingBefore(8);

			Chunk Project = new Chunk("PROJECTS", fontSideHeadings);
			Project.setUnderline(1f, -2f);

			Chunk Skills = new Chunk("SKILLS", fontSideHeadings);
			Skills.setUnderline(1f, -2f);

			Set<Project> projectDetails = experienced.getProjects();

			Set<Skill> skillsDetails = experienced.getSkills();

			PdfWriter.getInstance(document, out);
			document.open();
			document.add(pTitle);
			document.add(pName);
			document.add(pMailId);
			document.add(pPhone);
			document.add(pAdress);
			document.add(pSpace);
			document.add(education);
			document.add(pDegree);
			document.add(pHsc);
			document.add(pSSc);
			document.add(pSpace);
			document.add(workEx);
			document.add(pWorkExperience);
			document.add(pSpace);
			document.add(Project);

			for (Project pp : projectDetails) {
				Paragraph pProjects = new Paragraph("* " + pp.getProjects(), fontPersonalDetails);
				pProjects.setSpacingBefore(5);
				document.add(pProjects);
			}

			document.add(pSpace);
			document.add(Skills);

			for (Skill ss : skillsDetails) {
				Paragraph pSkills = new Paragraph("* " + ss.getSkills(), fontPersonalDetails);
				pSkills.setSpacingBefore(5);
				document.add(pSkills);
			}

			document.close();

		} catch (DocumentException ex) {

			System.out.println(ex);
		}

		return new ByteArrayInputStream(out.toByteArray());
	}

	public static ByteArrayInputStream centerAlignment(Experienced experienced) {
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {
			Font fontTitle = FontFactory.getFont(FontFactory.TIMES);
			fontTitle.setSize(20);

			Paragraph pTitle = new Paragraph("Resume", fontTitle);
			pTitle.setAlignment(Paragraph.ALIGN_TOP);
			pTitle.setAlignment(Paragraph.ALIGN_LEFT);
			pTitle.setSpacingAfter(8);

			Font fontPersonalDetails = FontFactory.getFont(FontFactory.COURIER_BOLDOBLIQUE);
			fontPersonalDetails.setSize(12);

			Paragraph pName = new Paragraph(experienced.getFirstName() + " " + experienced.getLastName(),
					fontPersonalDetails);
			pName.setAlignment(Paragraph.ALIGN_CENTER);

			Paragraph pMailId = new Paragraph(experienced.getEmail(), fontPersonalDetails);
			pMailId.setAlignment(Paragraph.ALIGN_CENTER);

			Paragraph pPhone = new Paragraph(experienced.getPhone(), fontPersonalDetails);
			pPhone.setAlignment(Paragraph.ALIGN_CENTER);

			Paragraph pAdress = new Paragraph(
					experienced.getCity() + ", " + experienced.getState() + "- " + experienced.getPincode(),
					fontPersonalDetails);
			pAdress.setAlignment(Paragraph.ALIGN_CENTER);
			pAdress.setSpacingAfter(10);
			Paragraph pSpace = new Paragraph(
					"----------------------------------------------------------------------------------------------------------------------------------");
			pSpace.setSpacingAfter(10);

			Font fontSideHeadings = FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 12, Font.NORMAL, BaseColor.BLUE);
			fontSideHeadings.setSize(15);

			Chunk education = new Chunk("EDUCATION", fontSideHeadings);
			education.setUnderline(1f, -2f);
			
			Paragraph peducation = new Paragraph(education);
			peducation.setAlignment(Paragraph.ALIGN_CENTER);

			Paragraph pDegree = new Paragraph(
					"* Degree : " + String.valueOf(experienced.getEducation().getDegreePercent()) + "%",
					fontPersonalDetails);
			pDegree.setAlignment(Paragraph.ALIGN_CENTER);
			pDegree.setSpacingBefore(8);

			Paragraph pHsc = new Paragraph("* HSC : " + String.valueOf(experienced.getEducation().getHsc()) + "%",
					fontPersonalDetails);
			pHsc.setAlignment(Paragraph.ALIGN_CENTER);
			pHsc.setSpacingBefore(5);

			Paragraph pSSc = new Paragraph("* SSC : " + String.valueOf(experienced.getEducation().getSsc()) + "%",
					fontPersonalDetails);
			pSSc.setAlignment(Paragraph.ALIGN_CENTER);
			pSSc.setSpacingBefore(5);

			Chunk workEx = new Chunk("WORK EXPERIENCE", fontSideHeadings);
			workEx.setUnderline(1f, -2f);
			
			Paragraph pWorkEx = new Paragraph(workEx);
			pWorkEx.setAlignment(Paragraph.ALIGN_CENTER);

			SimpleDateFormat formatFromDate = new SimpleDateFormat("d MMMM yyyy");
			String fromDate = formatFromDate.format(experienced.getWorkex().getFromDate());

			SimpleDateFormat formatToDate = new SimpleDateFormat("d MMMM yyyy");
			String toDate = formatToDate.format(experienced.getWorkex().getToDate());

			Paragraph pWorkExperience = new Paragraph(
					"I was working in " + experienced.getWorkex().getEmployerName() + " as "
							+ experienced.getWorkex().getJobTitle() + " in " + experienced.getWorkex().getWcity() + ", "
							+ experienced.getWorkex().getWstate() + ", from " + fromDate + " to " + toDate,
					fontPersonalDetails);
			pWorkExperience.setSpacingBefore(8);

			Chunk Project = new Chunk("PROJECTS", fontSideHeadings);
			Project.setUnderline(1f, -2f);
			
			Paragraph pProject = new Paragraph(Project);
			pProject.setAlignment(Paragraph.ALIGN_CENTER);

			Chunk Skills = new Chunk("SKILLS", fontSideHeadings);
			Skills.setUnderline(1f, -2f);
			
			Paragraph pSkill = new Paragraph(Skills);
			pSkill.setAlignment(Paragraph.ALIGN_CENTER);
			
			Set<Project> projectDetails = experienced.getProjects();

			Set<Skill> skillsDetails = experienced.getSkills();

			PdfWriter.getInstance(document, out);
			document.open();
			document.add(pTitle);
			document.add(pName);
			document.add(pMailId);
			document.add(pPhone);
			document.add(pAdress);
			document.add(pSpace);
			document.add(peducation);
			document.add(pDegree);
			document.add(pHsc);
			document.add(pSSc);
			document.add(pSpace);
			document.add(pWorkEx);
			document.add(pWorkExperience);
			document.add(pSpace);
			document.add(pProject);

			for (Project pp : projectDetails) {
				Paragraph pProjects = new Paragraph("* " + pp.getProjects(), fontPersonalDetails);
				pProjects.setAlignment(Paragraph.ALIGN_CENTER);
				pProjects.setSpacingBefore(5);
				document.add(pProjects);
			}

			document.add(pSpace);
			document.add(pSkill);

			for (Skill ss : skillsDetails) {
				Paragraph pSkills = new Paragraph("* " + ss.getSkills(), fontPersonalDetails);
				pSkills.setAlignment(Paragraph.ALIGN_CENTER);
				pSkills.setSpacingBefore(5);
				document.add(pSkills);
			}

			document.close();

		} catch (DocumentException ex) {

			System.out.println(ex);
		}

		return new ByteArrayInputStream(out.toByteArray());
	}

	public static ByteArrayInputStream rightAlignment(Experienced experienced) {
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {
			Font fontTitle = FontFactory.getFont(FontFactory.COURIER_BOLD);
			fontTitle.setSize(20);

			Paragraph pTitle = new Paragraph("Resume", fontTitle);
			pTitle.setAlignment(Paragraph.ALIGN_TOP);
			pTitle.setAlignment(Paragraph.ALIGN_LEFT);
			pTitle.setSpacingAfter(8);

			Font fontPersonalDetails = FontFactory.getFont(FontFactory.TIMES_BOLD);
			fontPersonalDetails.setSize(12);

			Paragraph pName = new Paragraph(experienced.getFirstName() + " " + experienced.getLastName(),
					fontPersonalDetails);
			pName.setAlignment(Paragraph.ALIGN_RIGHT);

			Paragraph pMailId = new Paragraph(experienced.getEmail(), fontPersonalDetails);
			pMailId.setAlignment(Paragraph.ALIGN_RIGHT);

			Paragraph pPhone = new Paragraph(experienced.getPhone(), fontPersonalDetails);
			pPhone.setAlignment(Paragraph.ALIGN_RIGHT);

			Paragraph pAdress = new Paragraph(
					experienced.getCity() + ", " + experienced.getState() + "- " + experienced.getPincode(),
					fontPersonalDetails);
			pAdress.setAlignment(Paragraph.ALIGN_RIGHT);
			pAdress.setSpacingAfter(10);
			Paragraph pSpace = new Paragraph(
					"----------------------------------------------------------------------------------------------------------------------------------");
			pSpace.setSpacingAfter(10);

			Font fontSideHeadings = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Font.NORMAL, BaseColor.RED);
			fontSideHeadings.setSize(15);

			Chunk education = new Chunk("EDUCATION", fontSideHeadings);
			education.setUnderline(1f, -2f);
			
			Paragraph peducation = new Paragraph(education);
			peducation.setAlignment(Paragraph.ALIGN_RIGHT);


			Paragraph pDegree = new Paragraph(
					"* Degree : " + String.valueOf(experienced.getEducation().getDegreePercent()) + "%",
					fontPersonalDetails);
			pDegree.setAlignment(Paragraph.ALIGN_RIGHT);
			pDegree.setSpacingBefore(8);
			
			Paragraph pHsc = new Paragraph("* HSC : " + String.valueOf(experienced.getEducation().getHsc()) + "%",
					fontPersonalDetails);
			pHsc.setAlignment(Paragraph.ALIGN_RIGHT);
			pHsc.setSpacingBefore(5);

			Paragraph pSSc = new Paragraph("* SSC : " + String.valueOf(experienced.getEducation().getSsc()) + "%",
					fontPersonalDetails);
			pSSc.setAlignment(Paragraph.ALIGN_RIGHT);
			pSSc.setSpacingBefore(5);

			Chunk workEx = new Chunk("WORK EXPERIENCE", fontSideHeadings);
			workEx.setUnderline(1f, -2f);
			
			Paragraph pWorkEx = new Paragraph(workEx);
			pWorkEx.setAlignment(Paragraph.ALIGN_RIGHT);

			SimpleDateFormat formatFromDate = new SimpleDateFormat("d MMMM yyyy");
			String fromDate = formatFromDate.format(experienced.getWorkex().getFromDate());

			SimpleDateFormat formatToDate = new SimpleDateFormat("d MMMM yyyy");
			String toDate = formatToDate.format(experienced.getWorkex().getToDate());

			Paragraph pWorkExperience = new Paragraph(
					"I was working in " + experienced.getWorkex().getEmployerName() + " as "
							+ experienced.getWorkex().getJobTitle() + " in " + experienced.getWorkex().getWcity() + ", "
							+ experienced.getWorkex().getWstate() + ", from " + fromDate + " to " + toDate,
					fontPersonalDetails);
			pWorkExperience.setSpacingBefore(8);

			Chunk Project = new Chunk("PROJECTS", fontSideHeadings);
			Project.setUnderline(1f, -2f);
			
			Paragraph pProject = new Paragraph(Project);
			pProject.setAlignment(Paragraph.ALIGN_RIGHT);


			Chunk Skills = new Chunk("SKILLS", fontSideHeadings);
			Skills.setUnderline(1f, -2f);
			
			Paragraph pSkill = new Paragraph(Skills);
			pSkill.setAlignment(Paragraph.ALIGN_RIGHT);

			Set<Project> projectDetails = experienced.getProjects();

			Set<Skill> skillsDetails = experienced.getSkills();

			PdfWriter.getInstance(document, out);
			document.open();
			document.add(pTitle);
			document.add(pName);
			document.add(pMailId);
			document.add(pPhone);
			document.add(pAdress);
			document.add(pSpace);
			document.add(peducation);
			document.add(pDegree);
			document.add(pHsc);
			document.add(pSSc);
			document.add(pSpace);
			document.add(pWorkEx);
			document.add(pWorkExperience);
			document.add(pSpace);
			document.add(pProject);

			for (Project pp : projectDetails) {
				Paragraph pProjects = new Paragraph("* " + pp.getProjects(), fontPersonalDetails);
				pProjects.setAlignment(Paragraph.ALIGN_RIGHT);
				pProjects.setSpacingBefore(5);
				document.add(pProjects);
			}

			document.add(pSpace);
			document.add(pSkill);

			for (Skill ss : skillsDetails) {
				Paragraph pSkills = new Paragraph("* " + ss.getSkills(), fontPersonalDetails);
				pSkills.setAlignment(Paragraph.ALIGN_RIGHT);
				pSkills.setSpacingBefore(5);
				document.add(pSkills);
			}

			document.close();

		} catch (DocumentException ex) {

			System.out.println(ex);
		}

		return new ByteArrayInputStream(out.toByteArray());
	}

}
