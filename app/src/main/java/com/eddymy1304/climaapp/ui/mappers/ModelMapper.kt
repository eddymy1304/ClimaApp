package com.eddymy1304.climaapp.ui.mappers

interface ModelMapper<Response, Model>  {
    fun toModel(response: Response): Model

}