package br.com.marcelodaniel.repository

import br.com.marcelodaniel.model.Cliente
import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import javax.persistence.EntityManager

@Repository
abstract class ClienteRepository(
    private val em: EntityManager
) : JpaRepository<Cliente, Long> {
    abstract fun findByNomeLike(nome: String, pageable: Pageable): Page<Cliente>

    @Query("select c from Cliente c")
    abstract fun listar(): List<Cliente>

    fun listarComImplementacao(nome: String?): List<Cliente> {
        var sql = "select c from Cliente c "

        if (nome != null) {
            sql += "where c.nome like :nome"
        }

        val query = em.createQuery(sql)

        if (nome != null) {
            query.setParameter("nome", "%$nome%")
        }

        val clientes = query.resultList
        return clientes as List<Cliente>
    }
}