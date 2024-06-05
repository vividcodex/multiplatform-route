package cn.vividcode.multiplatform.route.api.route

/**
 * CreateScope, you can get data and from.
 */
sealed interface CreateScope {
	
	val data: Map<String, *>
	
	val from: String
}

data class CreateScopeImpl(
	override val data: Map<String, *>,
	override val from: String
) : CreateScope