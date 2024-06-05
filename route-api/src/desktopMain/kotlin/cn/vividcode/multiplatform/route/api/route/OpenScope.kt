package cn.vividcode.multiplatform.route.api.route

/**
 * OpenScope, you can get data and from.
 */
interface OpenScope {

	val data: Map<String, *>
	
	val from: String
}

internal class OpenScopeImpl(
	override val data: Map<String, *>,
	override val from: String
) : OpenScope