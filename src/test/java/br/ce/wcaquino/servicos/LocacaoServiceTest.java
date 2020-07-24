package br.ce.wcaquino.servicos;


import static org.hamcrest.CoreMatchers.is;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqException;
import br.ce.wcaquino.exceptions.LocadoraException;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoServiceTest {
	
	private LocacaoService service;
	
	@Rule
	public ErrorCollector error = new ErrorCollector();
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Before
	public void setup() {
		service = new LocacaoService();
	}
	
	@Test
	public void test() throws Exception {
		//cenario
		LocacaoService service = new LocacaoService();
		Usuario user = new Usuario("Usuario 1");
		Filme filme = new Filme("Filme 1",1,5.0);
		
		//action
		Locacao locacao= service.alugarFilme(user, filme);
		//verificar
		error.checkThat(locacao.getValor(), is(5.0));
		/* Sem importação estatica
		* error.checkThat(locacao.getValor(), CoreMatchers.is(5.0));
		*/
		error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()),is(true));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)),is(true));
	}
	
	@Test(expected=FilmeSemEstoqException.class)
	public void testLocacao_filmeSemEstoq() throws Exception {
		LocacaoService service = new LocacaoService();
		Usuario user = new Usuario("Usuario 1");
		Filme filme = new Filme("Filme 1",0,5.0);
		//action
		service.alugarFilme(user, filme);
	}
	@Test
	public void testLocacaoUserVazio() throws FilmeSemEstoqException {
		LocacaoService service = new LocacaoService();
		Filme filme = new Filme("Filme 2",1,5.0);
		
		try {
			service.alugarFilme(null,filme);
			Assert.fail();
		} catch (LocadoraException e) {
			Assert.assertThat(e.getMessage(),is("Usuario vazio"));
		}
		
		System.out.println("Forma robusta");
		
	}
	@Test
	public void testLocacaoFilmeVazio() throws LocadoraException, FilmeSemEstoqException {
		LocacaoService service = new LocacaoService();
		Usuario user = new Usuario("Usuario 3");
		
		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio");
		
		service.alugarFilme(user, null);
		System.out.println("Forma nova");
	}
}
