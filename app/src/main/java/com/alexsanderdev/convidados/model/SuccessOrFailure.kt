/*
 * Copyright (c) 2023. Created by Alexsander at 11/15. All rights reserved.
 * GitHub: https://github.com/alexsanderfer/
 * Portfolio: https://alexsanderfer.netlify.app/
 ******************************************************************************/

package com.alexsanderdev.convidados.model

class SuccessOrFailure(var success: Boolean, var message: String, val isInsertion: Boolean) {
    // Construtor personalizado para criação simplificada
    constructor(success: Boolean, isInsertion: Boolean) : this(success, getMessage(success, isInsertion), isInsertion)

    companion object {
        // Função auxiliar para obter a mensagem com base no sucesso e tipo de operação
        private fun getMessage(success: Boolean, isInsertion: Boolean): String {
            return if (success) {
                if (isInsertion) {
                    "Inserção com sucesso"
                } else {
                    "Atualização com sucesso"
                }
            } else {
                "Falha na operação"
            }
        }
    }
}
