import java.net.Inet4Address
import java.net.NetworkInterface

fun main() {
    println(get_IP())
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
    }.also { ip ->
        println("IP Address: $ip") // Side effect using `also`
    }

}

