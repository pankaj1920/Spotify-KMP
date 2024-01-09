package com.spotify.app.feature_homepage.shared.domain.mapper.album

import com.spotify.app.core_base.shared.data.base.DtoMapper
import com.spotify.app.core_base.shared.data.dto.ImageDTO
import com.spotify.app.feature_homepage.shared.data.dto.album.AlbumItemDTO
import com.spotify.app.feature_homepage.shared.data.dto.album.ArtistDTO
import com.spotify.app.feature_homepage.shared.domain.model.album.AlbumItem

object AlbumItemDtoMapper : DtoMapper<AlbumItem, AlbumItemDTO> {
    override fun asDto(domain: AlbumItem): AlbumItemDTO {
        return AlbumItemDTO(
            artists = domain.artists.split(",").map { ArtistDTO(name = it) },
            id = domain.id,
            images = listOf(ImageDTO(url = domain.image)),
            name = domain.name,
            releaseDate = domain.releaseDate,
            totalTracks = domain.trackCount
        )
    }

    override fun asDomain(dto: AlbumItemDTO): AlbumItem {
        return AlbumItem(
            artists = dto.artists.joinToString(separator = ",") { it.name },
            id = dto.id,
            image = dto.images.firstOrNull()?.url.orEmpty(),
            name = dto.name,
            releaseDate = dto.releaseDate,
            trackCount = dto.totalTracks
        )
    }

}