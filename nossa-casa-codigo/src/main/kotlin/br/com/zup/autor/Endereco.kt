package br.com.zup.autor

import javax.persistence.Embeddable

@Embeddable
class Endereco(
    endercoResponse: EnderecoResponse,
    val numero: String) {

        val cep = endercoResponse.cep
        val logradouro = endercoResponse.logradouro
        var bairro = endercoResponse.bairro
        val localidade = endercoResponse.localidade
        val uf = endercoResponse.uf
        val ibge = endercoResponse.ibge


}
