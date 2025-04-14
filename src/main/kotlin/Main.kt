import java.net.Inet4Address
import java.net.NetworkInterface
import java.net.URL

fun main() {
    println(get_IP())
    println("public ip : ${get_public_IP()}")
}

fun get_IP(): String?{
    return try {
        NetworkInterface.getNetworkInterfaces()?.toList()?.flatMap { networkInterface ->
            networkInterface.inetAddresses.toList().filter {
                !it.isLoopbackAddress && it is Inet4Address
            }
        }?.firstOrNull()?.hostAddress
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}


fun get_public_IP(): String?{
    return try {
        URL("https://api.ipify.org").readText()
    } catch (e: Exception) {
        null
    }.run {
        this?.takeIf { it.isNotEmpty() }
    }
}

