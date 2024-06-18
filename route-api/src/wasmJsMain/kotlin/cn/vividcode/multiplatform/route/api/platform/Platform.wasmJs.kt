package cn.vividcode.multiplatform.route.api.platform

/**
 * currently running platform.
 */
internal actual val LocalPlatform: Platform by lazy { Web }