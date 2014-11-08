/**
 * Pacote que fornece a camada de interpretacao dos comandos.
 *
 * Essa camada recebe os comandos passados pelo usu&aacute;rio, atrav&eacute;s do socket e disponibiliza os comandos para a
 * classe inferior, onde os comandos ser&atilde;o trabalhados.
 *
 * Por facilitar a extensibilidade do c&oacute;digo, esse pacote n&atilde;o far&aacute; reconhecimento dos comandos, apenas far&aacute; a
 * separa&ccedil;&atilde;o do que &eacute; comando e o que &eacute; par&acirc;metro;
 *
 * Tamb&eacute;m &eacute; responsabilidade dessa classe informar para o usu&aacute;rio qual foi o status da solita&ccedil;&atilde;o do cliente,
 * por exemplo: OK, COMANDO INVALIDO [Obs.: Esses status ser&atilde;o fornecidos pela camada inferior;
 *
 *
 * O reconhecimento da validade do comando ser&aacute; feito pela camada inferior.
 */
/**
 * @author bruno
 *
 */
package br.ufg.inf.redes.interpretador;