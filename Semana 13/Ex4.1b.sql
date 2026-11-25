SELECT pedido.id_pedido,
       cliente.nome
FROM cliente
LEFT JOIN pedido
  ON cliente.id_cliente = pedido.id_cliente