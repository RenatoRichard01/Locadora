package br.ce.wcaquino.servicos;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import br.ce.wcaquino.entidades.Usuario;

public class AssertTest {
	
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Test
	public void test() {
	
		Assert.assertTrue(true);
		Assert.assertFalse(false);
		
		Assert.assertEquals(1,1);
		Assert.assertEquals(0.51234,0.51234, 0.01);
		Assert.assertEquals(Math.PI, 3.14,0.01);
		
		int i = 5;
		Integer i2 = 5;
		Assert.assertEquals(Integer.valueOf(i), i2);
		Assert.assertEquals(i, i2.intValue());
		
		Assert.assertEquals("bola","bola");
		Assert.assertTrue("bola".equalsIgnoreCase("Bola"));
		Assert.assertTrue("bola".startsWith("bola"));
		
		Usuario usr1 = new Usuario("usuario 1");
		Usuario usr2 = new Usuario("usuario 1");
		Usuario usr4 = usr2;
		Usuario usr3 = null;
		
		Assert.assertEquals(usr1,usr2);
		//verifica se objetos s�o de mesma instancia.
		Assert.assertSame(usr2, usr4);
		
		//Verificar se valores s�o nulos
		Assert.assertNull(usr3);
		
		//Utilizando do Not
		Assert.assertNotSame(usr1,usr2);
		Assert.assertNotNull(usr3);
		/*
		 * 		error.checkThat(locacao.getValor(), is(5.0));
		/* Sem importa��o estatica
		* error.checkThat(locacao.getValor(), CoreMatchers.is(5.0));
		*
		* error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()),is(true));
		* error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)),is(true));
		*/
	}
}
