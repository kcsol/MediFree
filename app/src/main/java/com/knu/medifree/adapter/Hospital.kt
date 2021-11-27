package com.knu.medifree.adapter

class Hospital {
    val hospitalName: String
    var hospitalId: String = "none"
    var bitmask: Int

    constructor(hospitalName: String, id: String, major_bit_flag: Int) {
        this.hospitalName = hospitalName
        this.hospitalId = id
        bitmask = major_bit_flag
    }

    constructor(hospitalName: String, major_bit_flag: Int) {
        this.hospitalName = hospitalName
        bitmask = major_bit_flag
    }

    constructor(hospital_name: String) {
        hospitalName = hospital_name
        bitmask = 0
    }


    fun setMajor(major: String) {
        if (major == MAJOR1_TAG) {
            if (bitmask and MAJOR1 != 0) bitmask += MAJOR1
        } else if (major == MAJOR2_TAG) {
            if (bitmask and MAJOR2 != 0) bitmask += MAJOR2
        } else if (major == MAJOR3_TAG) {
            if (bitmask and MAJOR3 != 0) bitmask += MAJOR3
        } else if (major == MAJOR4_TAG) {
            if (bitmask and MAJOR4 != 0) bitmask += MAJOR4
        } else if (major == MAJOR5_TAG) {
            if (bitmask and MAJOR5 != 0) bitmask += MAJOR5
        } else if (major == MAJOR6_TAG) {
            if (bitmask and MAJOR6 != 0) bitmask += MAJOR6
        } else if (major == MAJOR7_TAG) {
            if (bitmask and MAJOR7 != 0) bitmask += MAJOR7
        } else if (major == MAJOR8_TAG) {
            if (bitmask and MAJOR8 != 0) bitmask += MAJOR8
        }
    }

    fun setMajor(major_bit_flag: Int) {
        if (bitmask and major_bit_flag != 0) bitmask += major_bit_flag
    }

    override fun toString(): String {
        return "Hospital{" +
                "hospitalName='" + hospitalName + '\'' +
                ", id='" + hospitalId + '\'' +
                '}'
    }

    companion object {
        // Bit mask approach
        const val MAJOR1 = 1
        const val MAJOR1_TAG = "피부과"
        const val MAJOR2 = 2
        const val MAJOR2_TAG = "산부인과"
        const val MAJOR3 = 4
        const val MAJOR3_TAG = "정형외과"
        const val MAJOR4 = 8
        const val MAJOR4_TAG = "내과"
        const val MAJOR5 = 16
        const val MAJOR5_TAG = "비뇨기과"
        const val MAJOR6 = 32
        const val MAJOR6_TAG = "신경외과"
        const val MAJOR7 = 64
        const val MAJOR7_TAG = "안과"
        const val MAJOR8 = 128
        const val MAJOR8_TAG = "치과"
        fun getBitmaskByMajorTag(major: String): Int {
            if (major == MAJOR1_TAG) return MAJOR1 else if (major == MAJOR2_TAG) return MAJOR2 else if (major == MAJOR3_TAG) return MAJOR3 else if (major == MAJOR4_TAG) return MAJOR4 else if (major == MAJOR5_TAG) return MAJOR5 else if (major == MAJOR6_TAG) return MAJOR6 else if (major == MAJOR7_TAG) return MAJOR7 else if (major == MAJOR8_TAG) return MAJOR8
            return 0
        }
    }
}