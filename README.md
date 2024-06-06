# 基于 Kotlin Multiplatform 实现的路由框架

## 当前版本：0.1.0

## maven

cn.vividcode.multiplatform:route-api:0.1.0

## 使用教程

移动端

```kotlin
@Composable
fun MobileApp() {
    pages {
        this.startup = "/page1"
        
        register("/page1") {
            Page1()
        }
		
        register("/page2") {
            Page2()
        }
    }
}

@Composable
fun PageScope.Page1() {
	
    // 跳转到 Page2
    route("/page2")
	
    // 传递数据
    route("/page2", "id" to 1, "name" to "hi" /* ... */)
	
    // 如果说跳转过去需要把当前页面关闭
    route("/page2", finish = true)
	
    // 如果说要关闭之前的所有页面
    route("/page2", finishAll = true)
	
    // 接受从Page2返回的时候传递的参数，onBack内的方法只会执行一遍
    onBack { // this: BackScope ->
        if (from == "/page2") {
            val user: String by data
        }
    }
}

@Composable
fun PageScope.Page2() {
	
    // 返回 Page1
    back()
	
    // 返回的时候传递参数
    back("user" to "Your user.")
	
    // 返回两页
    back(depth = 2)
	
    // 返回到首页
    back(toFirst = true)
	
    // 返回的时候设置不同状态
    back(resultCode = 1)
	
    // 接受从Page1传递过来的参数，onCreate内的方法会在返回的时候重新执行
    var id by remember { mutableStateOf(0) }
    var name by remember { mutableStateOf("") }
    
    onCreate {
        if (from == "/page1") {
            id = this.data["id"] as Int
            name = this.data["name"] as String
        }
    }
}

```

桌面端

```kotlin
@Composable
fun ApplicationScope.DesktopApp() {
	
    windows {
        this.startup = "Window1"
		
        register(
            name = "Window1",
            size = WindowSize.fixed(width = 300.dp, height = 400.dp)
        ) { 
            Window1()
        }
    
        register(
            name = "Window2",
            size = WindowSize.resizable(
                width = 300.dp, 
                height = 400.dp,
                minWidth = 200.dp, // default: 0.dp
                minHeight = 300.dp // default: 0.dp
            )
        ) {
            Window2() 
        }
    }
}

@Composable
fun Window1() {
    pages(name = "Window1") {
        this.startup = "/window1/page1"
        
        register("/window1/page1") {
            Window1Page1()
        }
    }
}

@Composable
fun Window2() {
    pages(name = "Window2") {
        this.startup = "/window2/page1"
        
        register("/window2/page1") { 
            Window2Page1()
        } 
    }
}

@Composable
fun PageScope.Window1Page1() {
	
    // 从Window1中打开Window2
    open("Window2")
}

@Composable
fun PageScope.Window2Page1() {
	
    // 关闭当前窗口
    close()
}
```