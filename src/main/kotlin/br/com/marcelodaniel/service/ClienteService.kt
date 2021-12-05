package br.com.marcelodaniel.service

import br.com.marcelodaniel.exception.RegistroNaoEncontradoException
import br.com.marcelodaniel.model.Cliente
import br.com.marcelodaniel.repository.ClienteRepository
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import io.micronaut.http.annotation.*
import jakarta.inject.Singleton
import javax.transaction.Transactional

@Singleton
open class ClienteService(
    private val clienteRepository: ClienteRepository
) {

    fun create(cliente: Cliente): Cliente {
        return clienteRepository.save(cliente)
    }

    fun findAll(nome: String?, pageable: Pageable): Page<Cliente> {
        val clientes = if (nome == null) {
            clienteRepository.findAll(pageable)
        } else {
            clienteRepository.findByNomeLike("%$nome%", pageable)
        }
        return clientes
    }

    fun findById(id: Long): Cliente {
        return clienteRepository.findById(id).orElseThrow {
            RegistroNaoEncontradoException("Registro não encontrado")
        }
    }

    fun delete(id: Long) {
        val clienteDB = findById(id)
        clienteRepository.delete(clienteDB)
    }

    @Transactional
    open fun update(id: Long, @Body cliente: Cliente) {
        val clienteDB = findById(id)
        clienteDB.nome = cliente.nome
        clienteDB.documento = cliente.documento
        clienteDB.endereco = cliente.endereco
        clienteRepository.save(clienteDB)
    }

}