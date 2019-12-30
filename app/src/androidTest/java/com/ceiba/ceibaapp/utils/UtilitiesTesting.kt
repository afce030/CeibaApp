package com.ceiba.ceibaapp.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ceiba.ceibaapp.model.persistence.entities.posts.PostsEntity
import com.ceiba.ceibaapp.model.persistence.entities.users.AddressEntity
import com.ceiba.ceibaapp.model.persistence.entities.users.CompanyEntity
import com.ceiba.ceibaapp.model.persistence.entities.users.UsersEntity
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

object UtilitiesTesting {

    val user1 = UsersEntity(
        userId = 0,
        website = "anyweb.org",
        addressEntity = AddressEntity(
            zipcode = "99-3874",
            lng = "21.1496",
            lat = "-37.3159",
            suite = "Apt. 101",
            city = "Veracruz",
            street = "Calle falsa 123"
        ),
        phone = "2655281",
        name = "Leanne Ssssa",
        company = CompanyEntity(
            companyName = "Romaguera-Crona",
            bs = "harness real-time e-markets",
            catchPhrase = "Multi-layered client-server neural-net"
        ),
        email = "Si@wwf.dc",
        username = "Bet"
    )

    val user2 = UsersEntity(
        userId = 1,
        website = "hildegard.org",
        addressEntity = AddressEntity(
            zipcode = "92998-3874",
            lng = "6.2323",
            lat = "80.3159",
            suite = "Apt. 201",
            city = "New York",
            street = "Any Street"
        ),
        phone = "2655644",
        name = "NN",
        company = CompanyEntity(
            companyName = "Romaguera-Crona",
            bs = "Any",
            catchPhrase = "Any7"
        ),
        email = "Scere87657@wwe.biz",
        username = "Homer"
    )

    val user3 = UsersEntity(
        userId = 2,
        website = "myweb.org",
        addressEntity = AddressEntity(
            zipcode = "050030",
            lng = "8.1496",
            lat = "7.3159",
            suite = "Apt. 56",
            city = "Lima",
            street = "Kulas Light"
        ),
        phone = "993",
        name = "Barack Obama",
        company = CompanyEntity(
            companyName = "Corona",
            bs = "e-markets",
            catchPhrase = "NN client"
        ),
        email = "honest@mmm.ty",
        username = "Bart Ob"
    )

    val user4 = UsersEntity(
        userId = 3,
        website = "hildegard.org",
        addressEntity = AddressEntity(
            zipcode = "92998-3874",
            lng = "1.5688",
            lat = "-2.679",
            suite = "777",
            city = "Dallas",
            street = "jhjhj"
        ),
        phone = "288888999",
        name = "Alexander G",
        company = CompanyEntity(
            companyName = "Avianca",
            bs = "aa",
            catchPhrase = "any client"
        ),
        email = "June@May.com",
        username = "Alex"
    )


    val post1 = PostsEntity(
        postId = 0,
        userId = 0,
        title = "title1",
        body = "content1"
    )

    val post2 = PostsEntity(
        postId = 1,
        userId = 0,
        title = "title2",
        body = "content2"
    )

    val post3 = PostsEntity(
        postId = 2,
        userId = 1,
        title = "title3",
        body = "content3"
    )

    val post4 = PostsEntity(
        postId = 3,
        userId = 2,
        title = "title4",
        body = "content4"
    )

    val post5 = PostsEntity(
        postId = 4,
        userId = 3,
        title = "title5",
        body = "content5"
    )

}

//Generics function
fun <T> LiveData<T>.awaitValue(): T? {

    //Database content
    var data: T? = null
    //Wait for livedata
    val counter = CountDownLatch(1)

    observeForever{ t ->
        data = t
        counter.countDown()
    }

    counter.await(2, TimeUnit.SECONDS)
    return data
}