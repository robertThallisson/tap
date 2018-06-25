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
import org.springframework.stereotype.Service;
import org.tap.ueg.models.Aluno;
import org.tap.ueg.models.Frequencia;
import org.tap.ueg.models.auxi.Registro;
import org.tap.ueg.repository.AlunoRepository;
import org.tap.ueg.repository.FrequenciaRepository;

@Service
public class FrequenciaBS {
	private final LocalTime primeira = LocalTime.parse("19:00");
	private final LocalTime segunda = LocalTime.parse("19:45");

	private final LocalTime terceira = LocalTime.parse("20:45");
	private final LocalTime quarta = LocalTime.parse("21:30");
	private final LocalTime last = LocalTime.parse("22:15");

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
				if (list.get(0) == null)
					list.set(0, frequencia);

			}
			if ((frequencia.getRegistro().toLocalTime().isAfter(segunda))
					&& (frequencia.getRegistro().toLocalTime().isBefore(terceira))) {
				if (list.get(1) == null)
					list.set(1, frequencia);
			}
			if ((frequencia.getRegistro().toLocalTime().isAfter(terceira))
					&& (frequencia.getRegistro().toLocalTime().isBefore(quarta))) {
				if (list.get(2) == null)
					list.set(2, frequencia);
			}
			if ((frequencia.getRegistro().toLocalTime().isAfter(quarta))
					&& (frequencia.getRegistro().toLocalTime().isBefore(last))) {
				if (list.get(3) == null)
					list.set(3, frequencia);
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
			registro.setMatricula(aluno.getMatricula());
			for (Frequencia frequencia : aluno.getFrequencia()) {
				if (frequencia != null) {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

					String formatDateTime = frequencia.getRegistro().format(formatter);
					registro.getFrequencias().add(formatDateTime);
				} else {
					registro.getFrequencias().add(null);
				}

			}
			registros.add(registro);
		}
		return registros;

	}

	public List<Registro> findRegistroByData(String date) {
		// TODO Auto-generated method stub

		try {
			SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy");
			SimpleDateFormat df2 = new SimpleDateFormat("dd-MM-yyyy");
			System.out.println(df2.format(df.parse(date)));
			// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
			LocalDateTime dateTime = df2.parse(df2.format(df.parse(date))).toInstant().atZone(ZoneId.systemDefault())
					.toLocalDateTime();
			LocalDateTime inicio = LocalDateTime.of(dateTime.toLocalDate(), primeira);
			LocalDateTime terminio = LocalDateTime.of(dateTime.toLocalDate(), last);

			// Iterable<Aluno> frequencia =
			// ar.findByFrequenciaRegistroQuery(java.sql.Date.valueOf(dateTime.toLocalDate()),java.sql.Date.valueOf(dateTime.toLocalDate()));

			// Iterable<Aluno> frequencia = ar.findByFrequenciaQuery(dateTime, dateTime);
			// Iterable<Aluno> frequencia =
			// ar.findByFrequenciaQuery(Timestamp.valueOf(inicio),
			// Timestamp.valueOf(terminio));
			Iterable<Aluno> frequencia = ar.findAll();
			List<Aluno> alunos = new ArrayList<>();

			for (Aluno aluno : frequencia) {
				Aluno aluno2 = new Aluno();
				aluno2.setPessoa(aluno.getPessoa());
				aluno2.setFrequencia((List<Frequencia>) fr.findByAlunoAndRegistroBetween(aluno, inicio, terminio));
				aluno2.setFrequencia(ordenar(aluno2.getFrequencia()));
				aluno2.setMatricula(aluno.getMatricula());
				alunos.add(aluno2);
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

	public void registar(String digital) throws Exception {
		// TODO Auto-generated method stub
		try {
			Aluno aluno = ar.findByDigitaisNome(digital);

			if (aluno == null) {
				throw new Exception();
			}

			Frequencia frequencia = new Frequencia();
			frequencia.setAluno(aluno);
			frequencia.setRegistro(LocalDateTime.now());
			fr.save(frequencia);
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception();
		}
	}

	public LocalTime getPrimeira() {
		return primeira;
	}

	public LocalTime getSegunda() {
		return segunda;
	}

	public LocalTime getTerceira() {
		return terceira;
	}

	public LocalTime getQuarta() {
		return quarta;
	}

	public LocalTime getLast() {
		return last;
	}
}
