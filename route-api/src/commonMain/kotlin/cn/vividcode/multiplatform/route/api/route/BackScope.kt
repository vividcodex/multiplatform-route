package cn.vividcode.multiplatform.route.api.route

/**
 * BackScope, you can get data, resultCode and from.
 */
sealed interface BackScope {
	
	val data: Map<String, *>
	
	val resultCode: Int
	
	val from: String
}

data class BackScopeImpl(
	override val data: Map<String, *>,
	override val resultCode: Int,
	override val from: String
) : BackScope