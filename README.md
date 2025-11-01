# 🧩 Exercícios --- Query Methods com Spring Data JPA

## ⚙️ Interface Base do Repositório

``` java
public interface ProductRepository extends JpaRepository<Product, Long> {
}
```

------------------------------------------------------------------------

## 🧠 Exercícios --- Query Methods

### 🧩 Exercício 1 --- Consultas simples por descrição

**Objetivo:**\
Criar métodos que filtrem produtos com base no campo `description`.

**Tarefas:** 

1.Crie um método para buscar um produto pela descrição
exata.\

2.  Crie um método que busque todos os produtos que contenham parte da
    descrição.\

3.  Crie um método que busque todos os produtos cuja descrição comece
    com determinado texto.\

------------------------------------------------------------------------

### 🧩 Exercício 2 --- Consultas por preço

**Objetivo:**\
Praticar operadores de comparação com campos numéricos.

**Tarefas:** 

1.Buscar produtos com preço maior que um valor.\

2.  Buscar produtos com preço entre dois valores.\

3.  Buscar o produto mais caro.\


------------------------------------------------------------------------

### 🧩 Exercício 3 --- Consultas compostas

**Objetivo:**\
Usar múltiplas condições no mesmo método.

**Tarefas:** 

1. Buscar produtos com um `sku` específico e preço abaixo
de um valor.\

2.  Buscar produtos com determinada descrição **ou** com preço acima de
    um valor.\
    
------------------------------------------------------------------------

### 🧩 Exercício 4 --- Ordenação e Limites

**Objetivo:**\
Aprender a aplicar ordenação e limitar resultados.

**Tarefas:**

1. Buscar os 5 produtos mais baratos.\

2.  Buscar os 3 produtos mais caros.\

------------------------------------------------------------------------

### 🧩 Exercício 5 --- Consultas personalizadas com @Query

**Objetivo:**\
Utilizar JPQL para consultas customizadas.

**Tarefas:**

1. Buscar produtos cuja descrição contenha parte de um
texto (ignorando maiúsculas/minúsculas).\

2.  Buscar produtos por faixa de preço mínima e máxima.

------------------------------------------------------------------------

### 🧩 Exercício 6 --- Contagem e Existência

**Objetivo:**\
Aprender a usar métodos de agregação.

**Tarefas:**

1. Contar quantos produtos possuem preço acima de um
valor.\

2.  Verificar se existe um produto com determinado SKU.\

------------------------------------------------------------------------

### 💡 Desafio Final --- Filtro Dinâmico

Crie um endpoint `/products/search` que receba parâmetros opcionais:

-   `description`
-   `minPrice`
-   `maxPrice`

E retorne os produtos filtrados dinamicamente conforme os parâmetros
informados.

Sugestões: - Use **Specifications** (`JpaSpecificationExecutor`), ou -
Use uma query JPQL com `COALESCE` para tratar parâmetros nulos.