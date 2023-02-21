package com.example.bookshelf.network


import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class SearchResult(
    @SerialName("kind") val kind: String,
    @SerialName("totalItems") val totalItems: Long,
    @SerialName("items") val items: List<Item>,
)

@kotlinx.serialization.Serializable
data class Item(
    val kind: String? = null,
    val id: String? = null,
    val etag: String? = null,
    val selfLink: String? = null,
    @SerialName("volumeInfo") val volumeInfo: VolumeInfo? = null,
    val saleInfo: SaleInfo? = null,
    val accessInfo: AccessInfo? = null,
    val searchInfo: SearchInfo? = null,
)

@kotlinx.serialization.Serializable
data class VolumeInfo(
    val title: String? = null,
    @SerialName("subtitle") val subtitle: String? = null,
    val authors: List<String>? = null,
    val publishedDate: String? = null,
    @SerialName("description") val description: String? = null,
    val industryIdentifiers: List<IndustryIdentifier>? = null,
    val readingModes: ReadingModes? = null,
    val pageCount: Long? = null,
    val printType: String? = null,
    val printedPageCount: Int? = null,
    val categories: List<String>? = null,
    val maturityRating: String? = null,
    val allowAnonLogging: Boolean? = null,
    val contentVersion: String? = null,
    val language: String? = null,
    val previewLink: String? = null,
    val infoLink: String? = null,
    val canonicalVolumeLink: String? = null,
    @SerialName("publisher") val publisher: String? = null,
    @SerialName("panelizationSummary") val panelizationSummary: PanelizationSummary? = null,
    @SerialName("imageLinks") val imageLinks: ImageLinks? = null,
    @SerialName("averageRating") val averageRating: Long? = null,
    @SerialName("ratingsCount") val ratingsCount: Long? = null,
)

@kotlinx.serialization.Serializable
data class IndustryIdentifier(
    val type: String? = null,
    val identifier: String? = null,
)

@kotlinx.serialization.Serializable
data class ReadingModes(
    val text: Boolean,
    val image: Boolean,
)

@kotlinx.serialization.Serializable
data class PanelizationSummary(
    val containsEpubBubbles: Boolean,
    val containsImageBubbles: Boolean,
)

@kotlinx.serialization.Serializable
data class ImageLinks(
    val smallThumbnail: String? = null,
    val thumbnail: String? = null,
    val small: String? = null,
    val medium: String? = null,
    val large: String? = null,
    val extraLarge: String? = null
)

@kotlinx.serialization.Serializable
data class SaleInfo(
    val country: String,
    val saleability: String,
    val isEbook: Boolean,
    val listPrice: ListPrice? = null,
    val retailPrice: RetailPrice? = null,
    val buyLink: String? = null,
    val offers: List<Offer>? = null,
)

@kotlinx.serialization.Serializable
data class ListPrice(
    val amount: Double,
    val currencyCode: String,
)

@kotlinx.serialization.Serializable
data class RetailPrice(
    val amount: Double,
    val currencyCode: String,
)

@kotlinx.serialization.Serializable
data class Offer(
    val finskyOfferType: Long,
    val listPrice: ListPrice2,
    val retailPrice: RetailPrice2,
    val giftable: Boolean,
)

@kotlinx.serialization.Serializable
data class ListPrice2(
    val amountInMicros: Long,
    val currencyCode: String,
)

@kotlinx.serialization.Serializable
data class RetailPrice2(
    val amountInMicros: Long,
    val currencyCode: String,
)

@kotlinx.serialization.Serializable
data class AccessInfo(
    val country: String,
    val viewability: String,
    val embeddable: Boolean,
    val publicDomain: Boolean,
    val textToSpeechPermission: String,
    val epub: Epub,
    val pdf: Pdf,
    val webReaderLink: String,
    val accessViewStatus: String,
    val quoteSharingAllowed: Boolean,
    val downloadAccess: DownloadAccess? = null
)

@kotlinx.serialization.Serializable
data class DownloadAccess(
    val kind: String? = null,
    val volumeId: String? = null,
    val restricted: Boolean? = null,
    val deviceAllowed: Boolean? = null,
    val justAcquired: Boolean? = null,
    val maxDownloadDevices: Boolean? = null,
    val downloadsAcquired: Boolean? = null,
    val nonce: String? = null,
    val source: String? = null,
    val reasonCode: String? = null,
    val message: String? = null,
    val signature: String? = null
)

@kotlinx.serialization.Serializable
data class Epub(
    val isAvailable: Boolean,
    val downloadLink: String? = null,
    val acsTokenLink: String? = null,
)

@kotlinx.serialization.Serializable
data class Pdf(
    val isAvailable: Boolean,
    val downloadLink: String? = null,
    val acsTokenLink: String? = null,
)

@kotlinx.serialization.Serializable
data class SearchInfo(
    val textSnippet: String,
)