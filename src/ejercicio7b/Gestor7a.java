package ejercicio7b;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Gestor7a {
	Scanner sc = new Scanner(System.in);
	Alumno a1 = new Alumno();
	ArrayList<Alumno> alumnos = new ArrayList<>();

	public void crearAlumno() {
		//son alumnos, hago 2 para la prueba
		for (int i = 0; i < 2; i++) {

			a1 = new Alumno();
			System.out.println("Introduzca los siguientes datos:");

			System.out.println("Intoduzca el NIA");
			a1.setNia(sc.nextInt());

			sc.nextLine();

			System.out.println("Intoduzca el Nombre");
			a1.setNombre(sc.nextLine());

			System.out.println("Intoduzca el Apellidos");
			a1.setApellidos(sc.nextLine());

			System.out.println("Intoduzca el Genero");

			String genero = sc.nextLine();

			a1.setGenero(genero.charAt(0));

			System.out.println("Intoduzca el Fecha de nacimiento dd/mm/yyyy");

			String fecha = sc.nextLine();

			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

			try {
				a1.setFechaNac(formato.parse(fecha));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Intoduzca el Ciclo");
			a1.setCiclo(sc.nextLine());

			System.out.println("Intoduzca el Curso");
			a1.setCurso(sc.nextLine());

			System.out.println("Intoduzca el Grupo");
			a1.setGrupo(sc.nextLine());

			alumnos.add(a1);

		}

	}

	public void crearXml() {

		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document documento = builder.newDocument();

			Element rootElement = documento.createElement("alumnos");
			documento.appendChild(rootElement);

	        for (Alumno alumno : alumnos) {

	            Element alumnoElement = documento.createElement("alumno");
	            alumnoElement.setAttribute("nia", String.valueOf(alumno.getNia()));
	            alumnoElement.setAttribute("nombre", alumno.getNombre());
	            alumnoElement.setAttribute("apellidos", alumno.getApellidos());
	            alumnoElement.setAttribute("genero", String.valueOf(alumno.getGenero()));
	            alumnoElement.setAttribute("fechaNacimiento", String.valueOf(alumno.getFechaNac()));
	            alumnoElement.setAttribute("ciclo", alumno.getCiclo());
	            alumnoElement.setAttribute("curso", alumno.getCurso());
	            alumnoElement.setAttribute("grupo", alumno.getGrupo());

	            rootElement.appendChild(alumnoElement);
	        }
	        
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(documento);
			StreamResult result = new StreamResult(new File("alumnos.xml"));
			transformer.transform(source, result);
			System.out.println("XML generado");

		} catch (ParserConfigurationException | TransformerException e) {

			e.printStackTrace();

		}

	}

}
