SELECT produto.nome   AS nome_prod,
       produto.preco AS preco_prod,
       categoria.nome AS nome_cat
FROM produto
INNER JOIN categoria
  ON produto.id_categoria = categoria.id_categoria
  AND preco >= 500