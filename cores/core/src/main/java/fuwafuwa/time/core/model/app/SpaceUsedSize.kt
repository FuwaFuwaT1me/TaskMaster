package fuwafuwa.time.core.model.app

sealed interface SpaceUsedSize {

    val title: String
    val size: Long
}

data class AppSize(
    override val size: Long
): SpaceUsedSize {

    override val title: String = "App size"
}

data class DataSize(
    override val size: Long
): SpaceUsedSize {

    override val title: String = "Data size"
}

data class CacheSize(
    override val size: Long
): SpaceUsedSize {

    override val title: String = "Cache size"
}
