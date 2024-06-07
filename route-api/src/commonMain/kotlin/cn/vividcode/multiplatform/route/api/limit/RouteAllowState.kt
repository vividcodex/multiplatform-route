package cn.vividcode.multiplatform.route.api.limit

/**
 * restricted operation status of the route.
 */
object RouteAllowState {
	
	/**
	 * whether to allow color mode modification.
	 */
	var isAllowModificationColorMode = true
		internal set
	
	/**
	 * Whether you can use the route, you do not allow the route during the route switching process.
	 */
	var isAllowRoute = true
		internal set
}