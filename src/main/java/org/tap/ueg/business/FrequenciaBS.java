package org.tap.ueg.business;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.test.context.jdbc.Sql;
import org.tap.ueg.models.Aluno;
import org.tap.ueg.models.Frequencia;
import org.tap.ueg.models.auxi.Registro;
import org.tap.ueg.repository.AlunoRepository;
import org.tap.ueg.repository.FrequenciaRepository;

@Service
public class FrequenciaBS {
	private final LocalTime primeira = LocalTime.parse("19:00");
	private final LocalTime segunda = LocalTime.parse("19:00");
	private final LocalTime terceira = LocalTime.parse("19:00");
	private final LocalTime quarta = LocalTime.parse("19:00");
	private final LocalTime last = LocalTime.parse("19:00");

	@Autowired
	private FrequenciaRepository fr;
	@Autowired
	private AlunoRepository ar;

	public List<Frequencia> ordenar(List<Frequencia> frequencias) {
		List<Frequencia> list = new ArrayList<Frequencia>();
		list.add(null);
		list.add(null);
		list.add(null);
		list.add(null);
		for (Frequencia frequencia : frequencias) {
			if ((frequencia.getRegistro().toLocalTime().isAfter(primeira))
					&& (frequencia.getRegistro().toLocalTime().isBefore(segunda))) {
				list.add(0, frequencia);
			}
			if ((frequencia.getRegistro().toLocalTime().isAfter(segunda))
					&& (frequencia.getRegistro().toLocalTime().isBefore(terceira))) {
				list.add(1, frequencia);
			}
			if ((frequencia.getRegistro().toLocalTime().isAfter(terceira))
					&& (frequencia.getRegistro().toLocalTime().isBefore(quarta))) {
				list.add(2, frequencia);
			}
			if ((frequencia.getRegistro().toLocalTime().isAfter(quarta))
					&& (frequencia.getRegistro().toLocalTime().isBefore(last))) {
				list.add(3, frequencia);
			}
		}
		return list;

	}

	public List<Registro> toRegistro(List<Aluno> alunos) {
		List<Registro> registros = new ArrayList<Registro>();
		for (Aluno aluno : alunos) {

			Registro registro = new Registro();

			registro.setNome(aluno.getPessoa().getNome());
			registro.setFrequencias(new ArrayList<String>());
			for (Frequencia frequencia : aluno.getFrequencia()) {
				if (frequencia != null) {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

					String formatDateTime = frequencia.getRegistro().format(formatter);
					registro.getFrequencias().add(formatDateTime);
				} else {
					registro.getFrequencias().add("null");
				}

			}

		}
		return registros;

	}

	public List<Registro> findRegistroByData(String date) {
		// TODO Auto-generated method stub

		try {
			SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy");
			SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println(df2.format(df.parse(date)));
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
			LocalDateTime dateTime = df2.parse(df2.format(df.parse(date))).toInstant().atZone(ZoneId.systemDefault())
					.toLocalDateTime();

			// Iterable<Aluno> frequencia =
			// ar.findByFrequenciaRegistroQuery(java.sql.Date.valueOf(dateTime.toLocalDate()),java.sql.Date.valueOf(dateTime.toLocalDate()));

			//Iterable<Aluno> frequencia = ar.findByFrequenciaQuery(dateTime, dateTime);
			Iterable<Aluno> frequencia = ar.findByFrequenciaRegistro(dateTime);
			List<Aluno> alunos = new ArrayList<>();
			for (Aluno aluno : frequencia) {
				aluno.setFrequencia(ordenar(aluno.getFrequencia()));
				alunos.add(aluno);
			}

			return toRegistro(alunos);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public Aluno findByMatricula(String string) {
		// TODO Auto-generated method stub

		Aluno aluno = ar.findByMatricula(string);
		aluno.setFrequencia(ordenar(aluno.getFrequencia()));
		// alunos.add(aluno);

		return aluno;
	}

	public void registar(String digital) {
		// TODO Auto-generated method stub
		Aluno aluno = ar.findByDigitaisNome(digital);
		Frequencia frequencia = new Frequencia();
		frequencia.setAluno(aluno);
		frequencia.setRegistro(LocalDateTime.now());
		fr.save(frequencia);
	}
}
