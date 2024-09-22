package org.capnproto.examples

object Addressbook {

    class Person {
        class Factory : org.capnproto.StructFactory<Builder, Reader>() {
            override fun constructReader(
                segment: org.capnproto.SegmentReader,
                data: Int,
                pointers: Int,
                dataSize: Int,
                pointerCount: Short,
                nestingLimit: Int
            ): Reader {
                return Reader(segment, data, pointers, dataSize, pointerCount, nestingLimit)
            }

            override fun constructBuilder(
                segment: org.capnproto.SegmentBuilder,
                data: Int,
                pointers: Int,
                dataSize: Int,
                pointerCount: Short
            ): Builder {
                return Builder(segment, data, pointers, dataSize, pointerCount)
            }

            override fun structSize(): org.capnproto.StructSize {
                return Person.STRUCT_SIZE
            }

            override fun asReader(builder: Builder): Reader {
                return builder.asReader()
            }
        }

        class Builder(
            segment: org.capnproto.SegmentBuilder,
            data: Int,
            pointers: Int,
            dataSize: Int,
            pointerCount: Short
        ) : org.capnproto.StructBuilder(segment, data, pointers, dataSize, pointerCount) {

            fun asReader(): Reader {
                return Reader(segment, data, pointers, dataSize, pointerCount, Int.MAX_VALUE)
            }

            var id: Int
                get() = _getIntField(0)
                set(value) {
                    _setIntField(0, value)
                }

            var name: org.capnproto.Text.Builder?
                get() = _getPointerField(org.capnproto.Text.factory, 0, null, 0, 0)
                set(value) {
                    _setPointerField(org.capnproto.Text.factory, 0, value)
                }

            var email: org.capnproto.Text.Builder?
                get() = _getPointerField(org.capnproto.Text.factory, 1, null, 0, 0)
                set(value) {
                    _setPointerField(org.capnproto.Text.factory, 1, value)
                }

            val phones: org.capnproto.StructList.Builder<PhoneNumber.Builder>?
                get() = _getPointerField(Person.PhoneNumber.listFactory, 2, null, 0)

            val employment: Employment.Builder
                get() = Employment.Builder(segment, data, pointers, dataSize, pointerCount)

            fun initEmployment(): Employment.Builder {
                _setShortField(2, 0.toShort())
                _clearPointerField(3)
                return Employment.Builder(segment, data, pointers, dataSize, pointerCount)
            }
        }

        class Reader(
            segment: org.capnproto.SegmentReader,
            data: Int,
            pointers: Int,
            dataSize: Int,
            pointerCount: Short,
            nestingLimit: Int
        ) : org.capnproto.StructReader(segment, data, pointers, dataSize, pointerCount, nestingLimit) {
            val id: Int
                get() = _getIntField(0)

            val name: org.capnproto.Text.Reader?
                get() = _getPointerField(org.capnproto.Text.factory, 0, null, 0, 0)

            val email: org.capnproto.Text.Reader?
                get() = _getPointerField(org.capnproto.Text.factory, 1, null, 0, 0)

            val phones: org.capnproto.StructList.Reader<PhoneNumber.Reader>?
                get() = _getPointerField(Person.PhoneNumber.listFactory, 2, null, 0)

            val employment: Employment.Reader
                get() = Employment.Reader(segment, data, pointers, dataSize, pointerCount, nestingLimit)
        }

        object STRUCT_SIZE : org.capnproto.StructSize((1).toShort(), (4).toShort())

        object factory : Factory()

        object listFactory : org.capnproto.StructList.Factory<Builder, Reader>(factory)

        class PhoneNumber {
            object factory : Factory()

            object listFactory : org.capnproto.StructList.Factory<Builder, Reader>(factory)

            class Factory : org.capnproto.StructFactory<Builder, Reader>() {
                override fun constructReader(
                    segment: org.capnproto.SegmentReader,
                    data: Int,
                    pointers: Int,
                    dataSize: Int,
                    pointerCount: Short,
                    nestingLimit: Int
                ): Reader {
                    return Reader(segment, data, pointers, dataSize, pointerCount, nestingLimit)
                }

                override fun constructBuilder(
                    segment: org.capnproto.SegmentBuilder,
                    data: Int,
                    pointers: Int,
                    dataSize: Int,
                    pointerCount: Short
                ): Builder {
                    return Builder(segment, data, pointers, dataSize, pointerCount)
                }

                override fun structSize(): org.capnproto.StructSize {
                    return PhoneNumber.STRUCT_SIZE
                }

                override fun asReader(builder: Builder): Reader {
                    return builder.asReader()
                }
            }

            class Builder(
                segment: org.capnproto.SegmentBuilder,
                data: Int,
                pointers: Int,
                dataSize: Int,
                pointerCount: Short
            ) : org.capnproto.StructBuilder(segment, data, pointers, dataSize, pointerCount) {
                fun asReader(): Reader {
                    return Reader(segment, data, pointers, dataSize, pointerCount, Int.MAX_VALUE)
                }

                var number: org.capnproto.Text.Builder?
                    get() = _getPointerField(org.capnproto.Text.factory, 0, null, 0, 0)
                    set(value) {
                        _setPointerField(org.capnproto.Text.factory, 0, value)
                    }

                var type: Type
                    get() = when (_getShortField(0)) {
                        0 -> Type.MOBILE
                        1 -> Type.HOME
                        2 -> Type.WORK
                        else -> Type._NOT_IN_SCHEMA
                    }
                    set(value) {
                        _setShortField(0, value.ordinal.toShort())
                    }
            }

            class Reader(
                segment: org.capnproto.SegmentReader,
                data: Int,
                pointers: Int,
                dataSize: Int,
                pointerCount: Short,
                nestingLimit: Int
            ) : org.capnproto.StructReader(segment, data, pointers, dataSize, pointerCount, nestingLimit) {
                val number: org.capnproto.Text.Reader?
                    get() = _getPointerField(org.capnproto.Text.factory, 0, null, 0, 0)

                val type: Type
                    get() = when (_getShortField(0)) {
                        0 -> Type.MOBILE
                        1 -> Type.HOME
                        2 -> Type.WORK
                        else -> Type._NOT_IN_SCHEMA
                    }
            }

            enum class Type {
                MOBILE, HOME, WORK, _NOT_IN_SCHEMA
            }

            companion object {
                val STRUCT_SIZE = org.capnproto.StructSize(1.toShort(), 1.toShort())
            }
        }

        class Employment {
            object factory : Factory()

            object listFactory : org.capnproto.StructList.Factory<Builder, Reader>(factory)

            class Factory : org.capnproto.StructFactory<Builder, Reader>() {
                override fun constructReader(
                    segment: org.capnproto.SegmentReader,
                    data: Int,
                    pointers: Int,
                    dataSize: Int,
                    pointerCount: Short,
                    nestingLimit: Int
                ): Reader {
                    return Reader(segment, data, pointers, dataSize, pointerCount, nestingLimit)
                }

                override fun constructBuilder(
                    segment: org.capnproto.SegmentBuilder,
                    data: Int,
                    pointers: Int,
                    dataSize: Int,
                    pointerCount: Short
                ): Builder {
                    return Builder(segment, data, pointers, dataSize, pointerCount)
                }

                override fun structSize(): org.capnproto.StructSize {
                    return Employment.STRUCT_SIZE
                }

                override fun asReader(builder: Builder): Reader {
                    return builder.asReader()
                }
            }

            class Builder(
                segment: org.capnproto.SegmentBuilder,
                data: Int,
                pointers: Int,
                dataSize: Int,
                pointerCount: Short
            ) : org.capnproto.StructBuilder(segment, data, pointers, dataSize, pointerCount) {
                val which: Which
                    get() = when (_getShortField(2)) {
                        0 -> Which.UNEMPLOYED
                        1 -> Which.EMPLOYER
                        2 -> Which.SCHOOL
                        3 -> Which.SELF_EMPLOYED
                        else -> Which._NOT_IN_SCHEMA
                    }
            }

            class Reader(
                segment: org.capnproto.SegmentReader,
                data: Int,
                pointers: Int,
                dataSize: Int,
                pointerCount: Short,
                nestingLimit: Int
            ) : org.capnproto.StructReader(segment, data, pointers, dataSize, pointerCount, nestingLimit) {
                val which: Which
                    get() = when (_getShortField(2)) {
                        0 -> Which.UNEMPLOYED
                        1 -> Which.EMPLOYER
                        2 -> Which.SCHOOL
                        3 -> Which.SELF_EMPLOYED
                        else -> Which._NOT_IN_SCHEMA
                    }
            }

            enum class Which {
                UNEMPLOYED, EMPLOYER, SCHOOL, SELF_EMPLOYED, _NOT_IN_SCHEMA
            }

            companion object {
                val STRUCT_SIZE = org.capnproto.StructSize(1.toShort(), 4.toShort())
            }
        }
    }

    class AddressBook {
        object factory : Factory()

        object listFactory : org.capnproto.StructList.Factory<Builder, Reader>(factory)

        class Factory : org.capnproto.StructFactory<Builder, Reader>() {
            override fun constructReader(
                segment: org.capnproto.SegmentReader,
                data: Int,
                pointers: Int,
                dataSize: Int,
                pointerCount: Short,
                nestingLimit: Int
            ): Reader {
                return Reader(segment, data, pointers, dataSize, pointerCount, nestingLimit)
            }

            override fun constructBuilder(
                segment: org.capnproto.SegmentBuilder,
                data: Int,
                pointers: Int,
                dataSize: Int,
                pointerCount: Short
            ): Builder {
                return Builder(segment, data, pointers, dataSize, pointerCount)
            }

            override fun structSize(): org.capnproto.StructSize {
                return AddressBook.STRUCT_SIZE
            }

            override fun asReader(builder: Builder): Reader {
                return builder.asReader()
            }
        }

        class Builder(
            segment: org.capnproto.SegmentBuilder,
            data: Int,
            pointers: Int,
            dataSize: Int,
            pointerCount: Short
        ) : org.capnproto.StructBuilder(segment, data, pointers, dataSize, pointerCount) {
            val people: org.capnproto.StructList.Builder<org.capnproto.examples.Addressbook.Person.Builder>?
                get() = _getPointerField(org.capnproto.examples.Addressbook.Person.listFactory, 0, null, 0)
        }

        class Reader(
            segment: org.capnproto.SegmentReader,
            data: Int,
            pointers: Int,
            dataSize: Int,
            pointerCount: Short,
            nestingLimit: Int
        ) : org.capnproto.StructReader(segment, data, pointers, dataSize, pointerCount, nestingLimit) {
            val people: org.capnproto.StructList.Reader<org.capnproto.examples.Addressbook.Person.Reader>?
                get() = _getPointerField(org.capnproto.examples.Addressbook.Person.listFactory, 0, null, 0)
        }

        companion object {
            val STRUCT_SIZE = org.capnproto.StructSize(0.toShort(), 1.toShort())
        }
    }
}